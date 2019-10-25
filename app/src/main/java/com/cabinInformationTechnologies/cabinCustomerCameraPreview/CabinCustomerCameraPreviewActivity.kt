package com.cabinInformationTechnologies.cabinCustomerCameraPreview

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.cabinInformationTechnologies.cabin.R
import kotlinx.android.synthetic.main.cabin_customer_camerapreview.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CabinCustomerCameraPreviewActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerCameraPreview.CabinCustomerCameraPreviewContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerCameraPreview.CabinCustomerCameraPreviewContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerCameraPreview.CabinCustomerCameraPreviewPresenter(this)

    private val PERMISSION_REQUEST_CODE: Int = 101

    val REQUEST_FRONT_IMAGE_CAPTURE = 1
    val REQUEST_SIDE_IMAGE_CAPTURE = 2

    private var mCurrentPhotoPath: String? = null

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_camerapreview)
        presenter?.onCreate(intent.extras)

        setupPage()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_FRONT_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            //To get the File for further usage
            val auxFile = File(mCurrentPhotoPath)


            val bitmap: Bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
            camera_output_front.setImageBitmap(bitmap)

            presenter!!.takePictureOnRequest(REQUEST_SIDE_IMAGE_CAPTURE)

        } else if (requestCode == REQUEST_SIDE_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            //To get the File for further usage
            val auxFile = File(mCurrentPhotoPath)


            val bitmap: Bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
            camera_output_side.setImageBitmap(bitmap)
        }
    }

    //endregion

    //region View

    override fun setupPage() {
        camerapreview_capture_button.setOnClickListener{ presenter?.takePictureOnRequest(REQUEST_FRONT_IMAGE_CAPTURE) }
    }

    @TargetApi(15)
    override fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
            READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    @TargetApi(15)
    override fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA), PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        presenter?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun takePicture(requestCode: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File = createFile()

        val uri: Uri = FileProvider.getUriForFile(
            this,
            "com.example.android.fileprovider",
            file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        ActivityCompat.startActivityForResult(this, intent, requestCode, null)
    }

    override fun setMCurrentPhotoPath(path: String?) {
        this.mCurrentPhotoPath = path
    }

    @Throws(IOException::class)
    override fun createFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = File(getActivityContext()?.getExternalFilesDir(
            Environment.DIRECTORY_PICTURES), "Cabin")

        storageDir.mkdirs()
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            setMCurrentPhotoPath(absolutePath)
        }
    }

    //endregion
}