package com.cabinInformationTechnologies.cabinCustomerCamera

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import io.fotoapparat.selector.front
import io.fotoapparat.selector.off
import io.fotoapparat.selector.torch
import io.fotoapparat.view.CameraView
import com.cabinInformationTechnologies.cabin.R
import kotlinx.android.synthetic.main.cabin_customer_camera.*
import java.io.File


class CabinCustomerCameraActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.View, SensorEventListener {


    /* sensors */
    private var mSensorManager: SensorManager? = null
    private var mRotationSensor: Sensor? = null

    private val SENSOR_DELAY = 500 * 1000 // 500ms
    private val FROM_RADS_TO_DEGS = -57

    /* camera */
    val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    var fotoapparat: Fotoapparat? = null
    val filename = "test.png"
    val sd = Environment.getExternalStorageDirectory()
    val dest = File(sd, filename)
    var fotoapparatState : com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FotoapparatState? = null
    var cameraStatus : com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState? = null
    var flashState: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState? = null

    val constraintSet = ConstraintSet()


    var presenter: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_camera)
        presenter?.onCreate(intent.extras)

        setupPage()
    }

    override fun onStart() {
        super.onStart()
        if (hasNoPermissions()) {
            requestPermission()
        }else{
            fotoapparat?.start()
            fotoapparatState =
                com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FotoapparatState.ON
        }
    }

    override fun onResume() { //TODO: open camera again
        super.onResume()
        presenter?.onResume()

        if(!hasNoPermissions() && fotoapparatState == com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FotoapparatState.OFF){
            val intent = Intent(baseContext, com.cabinInformationTechnologies.cabin.MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        fotoapparat?.stop()
        com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FotoapparatState.OFF
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // TODO Auto-generated method stub
    }

    override fun onSensorChanged(event: SensorEvent) { //TODO: structural issues
        if (event.sensor == mRotationSensor) {
            if (event.values.size > 4) {
                val truncatedRotationVector = FloatArray(4)
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4)
                update(truncatedRotationVector) //TODO: performance
            } else {
                update(event.values)
            }
        }
    }

    //endregion

    //region View

    private fun setupPage() {
        // sensors
        try {
            mSensorManager = getSystemService(Activity.SENSOR_SERVICE) as SensorManager
            mRotationSensor = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
            mSensorManager!!.registerListener(this, mRotationSensor, SENSOR_DELAY)
        } catch (e: Exception) {
            Toast.makeText(this, "Hardware compatibility JSONIssue", Toast.LENGTH_LONG).show()
        }

        // camera
        createFotoapparat()

        cameraStatus = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState.BACK
        flashState = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState.OFF
        fotoapparatState =
            com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FotoapparatState.OFF

        capture_button.setOnClickListener { takePhoto() }
        switch_camera_button.setOnClickListener { switchCamera() }
        flash_button.setOnClickListener { changeFlashState() }

        constraintSet.clone(camera_layout)
    }

    override fun hasNoPermissions(): Boolean{
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(){
        ActivityCompat.requestPermissions(this, permissions,0)
    }

    /* REGION sensors */
    private fun update(vectors: FloatArray) {
        val rotationMatrix = FloatArray(9)
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors)
        val worldAxisX = SensorManager.AXIS_X
        val worldAxisZ = SensorManager.AXIS_Z
        val adjustedRotationMatrix = FloatArray(9)
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix)
        val orientation = FloatArray(3)
        SensorManager.getOrientation(adjustedRotationMatrix, orientation)
        val pitch = orientation[1] * FROM_RADS_TO_DEGS
        val roll = orientation[2] * FROM_RADS_TO_DEGS
        presenter?.cameraSwitch(pitch, roll)
        presenter?.indicate(pitch, roll)
    }

    /* REGION camera */
    private fun createFotoapparat(){
        val cameraView = findViewById<CameraView>(R.id.camera_view)

        fotoapparat = Fotoapparat(
            context = this,
            view = cameraView,
            scaleType = ScaleType.CenterCrop,
            lensPosition = back(),
            logger = loggers(
                logcat()
            ),
            cameraErrorCallback = { error ->
                println("Recorder errors: $error")
            }
        )
    }

    private fun takePhoto() {
        presenter?.checkPermissions()
        fotoapparat
            ?.takePicture()
            ?.saveToFile(dest)
    }

    private fun switchCamera() {
        fotoapparat?.switchTo(
            lensPosition =  if (cameraStatus == com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState.BACK) front() else back(),
            cameraConfiguration = CameraConfiguration()
        )
        presenter?.switchCamera(cameraStatus)
    }

    override fun frontCamera() {
        cameraStatus = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState.FRONT
    }

    override fun backCamera() {
        cameraStatus = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState.BACK
    }

    private fun changeFlashState() {
        fotoapparat?.updateConfiguration(
            CameraConfiguration(
                flashMode = if(flashState == com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState.TORCH) off() else torch()
            )
        )

        presenter?.switchFlash(flashState)
    }

    override fun flashOff() {
        flashState = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState.OFF
    }

    override fun flashOn() {
        flashState = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState.OFF
    }

    override fun disableCamera () {
        capture_button.isEnabled = false
        capture_button.isClickable = false
    }

    override fun enableCamera () {
        capture_button.isEnabled = true
        capture_button.isClickable = true
    }

    override fun setIndicatorVerticalPosition(pitch: Float) {
        constraintSet.setVerticalBias(capture_indicator.id, (0.9f + (pitch/100f)))
        constraintSet.applyTo(camera_layout)
    }

    override fun setIndicatorRoll(roll: Float) {
        capture_indicator.rotation = roll
    }

    override fun setIndicatorAlpha(alpha: Float) {
        capture_indicator.alpha = alpha
    }

    //endregion

    enum class CameraState{
        FRONT, BACK
    }

    enum class FlashState{
        TORCH, OFF
    }

    enum class FotoapparatState{
        ON, OFF
    }
}