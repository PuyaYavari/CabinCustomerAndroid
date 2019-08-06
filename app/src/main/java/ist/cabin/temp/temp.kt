package ist.cabin.temp

import android.os.Bundle
import android.widget.Toast
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.temp.*


class tempActivity : BaseActivity() { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!


    /*private var _bg__pending_orders: View? = null
    private var navbar_background: View? = null
    private var homepage_icon2: ImageView? = null
    private var homepage_icon1: ImageView? = null
    private var __p__homepage_label: TextView? = null
    private var cart_icon2: ImageView? = null
    private var cart_icon1: ImageView? = null
    private var __p__cart_label: TextView? = null
    private var __p__favorites_label: TextView? = null
    private var favorites_icon: ImageView? = null
    private var order_icon2: ImageView? = null
    private var order_icon1: ImageView? = null
    private var __p__order_label: TextView? = null
    private var __p__discover_label: TextView? = null
    private var discover_icon: ImageView? = null
    private var header_background: View? = null
    private var header_label: TextView? = null
    private var tabbar_seperator: ImageView? = null
    private var tabbar_pending_label: TextView? = null
    private var tabbar_shipping_label: TextView? = null
    private var tabbar_sent_label: TextView? = null
    private var tabbar_indicator: View? = null
    private var sorting_frame: View? = null
    private var sorting_label: TextView? = null
    private var sorting_icon2: View? = null
    private var sorting_icon1: ImageView? = null
    private var order1_background: View? = null
    private var order1_orders_picture: ImageView? = null
    private var order1_order_count_background: View? = null
    private var order1_order_count_label: TextView? = null
    private var order1_date: TextView? = null
    private var order1_payment_type: TextView? = null
    private var order1_datetime: TextView? = null
    private var order1_price_label: TextView? = null
    private var order2_background: View? = null
    private var order2_orders_picture: ImageView? = null
    private var order2_order_count_background: View? = null
    private var order2_order_count_label: TextView? = null
    private var order2_date: TextView? = null
    private var order2_payment_type: TextView? = null
    private var order2_datetime: TextView? = null
    private var order2_price_label: TextView? = null*/

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.temp)


        /*_bg__pending_orders = findViewById(R.id._bg__pending_orders) as View
        navbar_background = findViewById(R.id.navbar_background) as View
        homepage_icon2 = findViewById(R.id.homepage_icon2) as ImageView
        homepage_icon1 = findViewById(R.id.homepage_icon1) as ImageView
        __p__homepage_label = findViewById(R.id.__p__homepage_label) as TextView
        cart_icon2 = findViewById(R.id.cart_icon2) as ImageView
        cart_icon1 = findViewById(R.id.cart_icon1) as ImageView
        __p__cart_label = findViewById(R.id.__p__cart_label) as TextView
        __p__favorites_label = findViewById(R.id.__p__favorites_label) as TextView
        favorites_icon = findViewById(R.id.favorites_icon) as ImageView
        order_icon2 = findViewById(R.id.order_icon2) as ImageView
        order_icon1 = findViewById(R.id.order_icon1) as ImageView
        __p__order_label = findViewById(R.id.__p__order_label) as TextView
        __p__discover_label = findViewById(R.id.__p__discover_label) as TextView
        discover_icon = findViewById(R.id.discover_icon) as ImageView
        header_background = findViewById(R.id.header_background) as View
        header_label = findViewById(R.id.header_label) as TextView
        tabbar_seperator = findViewById(R.id.tabbar_seperator) as ImageView
        tabbar_pending_label = findViewById(R.id.tabbar_pending_label) as TextView
        tabbar_shipping_label = findViewById(R.id.tabbar_shipping_label) as TextView
        tabbar_sent_label = findViewById(R.id.tabbar_sent_label) as TextView
        tabbar_indicator = findViewById(R.id.tabbar_indicator) as View
        sorting_frame = findViewById(R.id.sorting_frame) as View
        sorting_label = findViewById(R.id.sorting_label) as TextView
        sorting_icon2 = findViewById(R.id.sorting_icon2) as View
        sorting_icon1 = findViewById(R.id.sorting_icon1) as ImageView
        order1_background = findViewById(R.id.order1_background) as View
        order1_orders_picture = findViewById(R.id.order1_orders_picture) as ImageView
        order1_order_count_background = findViewById(R.id.order1_order_count_background) as View
        order1_order_count_label = findViewById(R.id.order1_order_count_label) as TextView
        order1_date = findViewById(R.id.order1_date) as TextView
        order1_payment_type = findViewById(R.id.order1_payment_type) as TextView
        order1_datetime = findViewById(R.id.order1_datetime) as TextView
        order1_price_label = findViewById(R.id.order1_price_label) as TextView
        order2_background = findViewById(R.id.order2_background) as View
        order2_orders_picture = findViewById(R.id.order2_orders_picture) as ImageView
        order2_order_count_background = findViewById(R.id.order2_order_count_background) as View
        order2_order_count_label = findViewById(R.id.order2_order_count_label) as TextView
        order2_date = findViewById(R.id.order2_date) as TextView
        order2_payment_type = findViewById(R.id.order2_payment_type) as TextView
        order2_datetime = findViewById(R.id.order2_datetime) as TextView
        order2_price_label = findViewById(R.id.order2_price_label) as TextView*/

        order1.isClickable = true
        order1.setOnClickListener { Toast.makeText(this,"asd", Toast.LENGTH_SHORT).show() }

        //custom code goes here

    }

}