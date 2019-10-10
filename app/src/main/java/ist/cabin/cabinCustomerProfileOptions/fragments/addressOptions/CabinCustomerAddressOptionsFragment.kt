@file:Suppress("DEPRECATION")

package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.CabinCustomerAddressOptionsAdapter
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.NoAddressBox
import ist.cabin.cabincustomer.R



class CabinCustomerAddressOptionsFragment : BaseFragment(), CabinCustomerAddressOptionsContracts.View {

    var presenter: CabinCustomerAddressOptionsContracts.Presenter? = CabinCustomerAddressOptionsPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewAdapter: CabinCustomerAddressOptionsAdapter

    private val touchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder, target: ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                // move item in `fromPos` to `toPos` in adapter.
                return true// true if moved, false otherwise
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT)
                    viewAdapter.deleteItem(viewHolder.adapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val deleteIcon = resources.getDrawable(R.drawable.icon_delete_sweep)
                val intrinsicWidth = deleteIcon.intrinsicWidth
                val intrinsicHeight = deleteIcon.intrinsicHeight
                val background = ColorDrawable()
                val backgroundColor = resources.getColor(R.color.colorStateRed)

                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val isCanceled = dX == 0f && !isCurrentlyActive

                if (isCanceled) {
                    clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    return
                }

                // Draw the red delete background
                background.color = backgroundColor
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                background.draw(c)

                // Calculate position of delete icon
                val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val deleteIconMargin = (itemHeight - intrinsicHeight) / 8
                val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
                val deleteIconRight = itemView.right - deleteIconMargin
                val deleteIconBottom = deleteIconTop + intrinsicHeight

                if (dX < -2*intrinsicWidth) {
                    // Draw the delete icon
                    deleteIcon.setBounds(
                        deleteIconLeft,
                        deleteIconTop,
                        deleteIconRight,
                        deleteIconBottom
                    )
                    deleteIcon.draw(c)
                }

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
                c?.drawRect(left, top, right, bottom, clearPaint)
            }
        })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_address_options, container, false)
        setupPage()
        return pageView
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        presenter?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onCreate()
    }

    //region View

    private fun setupPage() {
        val context = this.context
        if (context != null)
            presenter?.getAddresses(context)

        pageView.findViewById<ImageButton>(R.id.address_options_back_button)
            .setOnClickListener { activity!!.onBackPressed() }
        recyclerView = pageView.findViewById(R.id.address_options_recycler)
        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button)
            .setOnClickListener { presenter?.setupDeliveryAddressList() }
        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button)
            .setOnClickListener { presenter?.setupInvoiceAddressList() }
    }

    override fun setupEmptyDeliveryAddressList() {
        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {

            background = resources.getDrawable(R.drawable.default_button_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {

            background = resources.getDrawable(R.drawable.default_button_unselected_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = false
            isEnabled = false
            isFocusable = false
            setOnClickListener { presenter?.moveToAddDeliveryAddressPage(null) }
        }
        pageView.findViewById<ConstraintLayout>(R.id.address_options_footer_layout).visibility = View.GONE

        val myDataset: MutableList<CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf(NoAddressBox(false))

        val viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)

        touchHelper.attachToRecyclerView(null)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupDeliveryAddressList(myDataset: MutableList<CabinCustomerAddressOptionsContracts.Addressbox>) {
        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {

            background = resources.getDrawable(R.drawable.default_button_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {

            background = resources.getDrawable(R.drawable.default_button_unselected_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = true
            isEnabled = true
            isFocusable = true
            text = resources.getText(R.string.add_delivery_address)
            setOnClickListener { presenter?.moveToAddDeliveryAddressPage(null) }
        }

        val viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)

        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupEmptyInvoiceAddressList() {
        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {

            background = resources.getDrawable(R.drawable.default_button_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {

            background = resources.getDrawable(R.drawable.default_button_unselected_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = false
            isEnabled = false
            isFocusable = false
            setOnClickListener { presenter?.moveToAddInvoiceAddressPage(null) }
        }
        pageView.findViewById<ConstraintLayout>(R.id.address_options_footer_layout).visibility = View.GONE

        val myDataset: MutableList<CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf(NoAddressBox(true))

        val viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)

        touchHelper.attachToRecyclerView(null)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupInvoiceAddressList(myDataset: MutableList<CabinCustomerAddressOptionsContracts.Addressbox>) {
        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {
            background = resources.getDrawable(R.drawable.default_button_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonLabelsPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {
            background = resources.getDrawable(R.drawable.default_button_unselected_background, context.theme)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorButtonUnselectedLabelPrimary))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = true
            isEnabled = true
            isFocusable = true
            text = resources.getText(R.string.add_invoice_address)
            setOnClickListener { presenter?.moveToAddInvoiceAddressPage(null) }
        }

        val viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)

        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun addDeliveryAddressListener(address: MODELAddress?) {
        presenter?.moveToAddDeliveryAddressPage(address)
    }

    override fun addInvoiceAddressListener(address: MODELAddress?) {
        presenter?.moveToAddInvoiceAddressPage(address)
    }

    override fun removeAddress(address: MODELAddress?) {
        val context = this.context
        if (context != null && address != null)
            presenter?.removeAddress(context, address)
    }

    override fun undoAddressRemove() {
        viewAdapter.undoDelete()
    }
    //endregion
}