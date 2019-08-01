package ist.cabin.cabinCustomerManualMeasureInput

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_manualmeasureinput.*


class CabinCustomerManualMeasureInputActivity : BaseActivity(),
    CabinCustomerManualMeasureInputContracts.View {

    var presenter: CabinCustomerManualMeasureInputContracts.Presenter? = CabinCustomerManualMeasureInputPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_manualmeasureinput)
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

    //endregion

    //region View

    override fun setupPage() {
        try {
            presenter?.setupMeasuresList(presenter!!.getMeasures())
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
        }
    }

    override fun addToMeasuresList(inputBox: View) {
        try {
            manualmeasureinput_input_layout.addView(inputBox)
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
        }
    }

    override fun createTextInputBox(name: String): View {
        val boxParent = ConstraintLayout(this)
        val background = ImageView(this)
        val text = TextView(this)
        val editText = EditText(this)
        val centimeter = TextView(this)

        val constraintSet = ConstraintSet()

        val listTextSize = 8f

        val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(1,6,1,6)

        boxParent.layoutParams = params

        background.id = R.id.cabin_customer_grey_text_background
        background.layoutParams = ConstraintLayout.LayoutParams((180 * this.resources.displayMetrics.density).toInt(),
            (35 * this.resources.displayMetrics.density).toInt())
        background.setBackgroundResource(R.drawable.cabin_grey_text_background)

        text.id = R.id.cabin_customer_measure_name_text
        text.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        text.text = name
        text.textSize = presenter?.calculateTextSize(name, listTextSize)!!.times(TypedValue.COMPLEX_UNIT_SP)
        text.setTextColor(ContextCompat.getColor(this, R.color.white))
        text.typeface = ResourcesCompat.getFont(this, R.font.century_gothic)

        editText.id = R.id.cabin_custom_measure_input
        editText.layoutParams = ConstraintLayout.LayoutParams((70 * this.resources.displayMetrics.density).toInt(),
            (30 * this.resources.displayMetrics.density).toInt())
        editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        editText.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(3, 1))
        editText.setBackgroundResource(R.drawable.cabin_white_text_background)
        editText.setEms(10)
        editText.setPadding((5 * this.resources.displayMetrics.density).toInt(),
            (1 * this.resources.displayMetrics.density).toInt(),
            (25 * this.resources.displayMetrics.density).toInt(),
            (0 * this.resources.displayMetrics.density).toInt())
        editText.textSize = listTextSize * TypedValue.COMPLEX_UNIT_SP

        centimeter.id = R.id.centimeter_abbrevation
        centimeter.text = resources.getText(R.string.centimeters_abbreviation)
        centimeter.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        centimeter.textSize = listTextSize * TypedValue.COMPLEX_UNIT_SP
        centimeter.setTextColor(ContextCompat.getColor(this, R.color.cabin_purple))
        centimeter.typeface = ResourcesCompat.getFont(this, R.font.century_gothic)

        boxParent.addView(background)
        boxParent.addView(text)
        boxParent.addView(editText)
        boxParent.addView(centimeter)

        constraintSet.clone(boxParent)

        constraintSet.connect(background.id, ConstraintSet.LEFT, boxParent.id, ConstraintSet.LEFT, 0)
        constraintSet.connect(background.id, ConstraintSet.RIGHT, boxParent.id, ConstraintSet.RIGHT, 0)
        constraintSet.setHorizontalBias(text.id, 0.5f)

        constraintSet.connect(text.id, ConstraintSet.TOP, background.id, ConstraintSet.TOP, 0)
        constraintSet.connect(text.id, ConstraintSet.BOTTOM, background.id, ConstraintSet.BOTTOM, 0)
        constraintSet.connect(text.id, ConstraintSet.LEFT, background.id, ConstraintSet.LEFT, 0)
        constraintSet.connect(text.id, ConstraintSet.RIGHT, editText.id, ConstraintSet.LEFT, 0)
        constraintSet.setHorizontalBias(text.id, 0.5f)
        constraintSet.setVerticalBias(text.id, 0.5f)

        constraintSet.connect(editText.id, ConstraintSet.TOP, background.id, ConstraintSet.TOP, 0)
        constraintSet.connect(editText.id, ConstraintSet.BOTTOM, background.id, ConstraintSet.BOTTOM, 0)
        constraintSet.connect(editText.id, ConstraintSet.LEFT, background.id, ConstraintSet.LEFT, 0)
        constraintSet.connect(editText.id, ConstraintSet.RIGHT, background.id, ConstraintSet.RIGHT, 0)
        constraintSet.setHorizontalBias(editText.id, 0.98f)

        constraintSet.connect(centimeter.id, ConstraintSet.TOP, editText.id, ConstraintSet.TOP, 0)
        constraintSet.connect(centimeter.id, ConstraintSet.BOTTOM, editText.id, ConstraintSet.BOTTOM, 0)
        constraintSet.connect(centimeter.id, ConstraintSet.LEFT, editText.id, ConstraintSet.LEFT, 0)
        constraintSet.connect(centimeter.id, ConstraintSet.RIGHT, editText.id, ConstraintSet.RIGHT, 0)
        constraintSet.setHorizontalBias(centimeter.id, 0.95f)

        constraintSet.applyTo(boxParent)

        return boxParent
    }

    //endregion
}