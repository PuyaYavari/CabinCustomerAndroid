package ist.cabin.cabinCustomerFinishTrade

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R



class CabinCustomerFinishTradeActivity : BaseActivity(),
    CabinCustomerFinishTradeContracts.View {

    var presenter: CabinCustomerFinishTradeContracts.Presenter? =
        CabinCustomerFinishTradePresenter(this)
    private lateinit var mPager: ViewPager

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_finish_trade_main)
        presenter?.onCreate(intent.extras)

        mPager = findViewById(R.id.finish_trade_pager)
        val pagerAdapter =
            CabinCustomerFinishTradePagerAdapter(
                supportFragmentManager, 0
            )
        mPager.setOnTouchListener { _, _ -> true }
        mPager.adapter = pagerAdapter

        findViewById<Button>(R.id.finish_trade_button).setOnClickListener {
            if (mPager.currentItem < 2)
                mPager.currentItem += 1
        }
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

    override fun hidePriceDetail() {
        findViewById<ConstraintLayout>(R.id.finish_trade_price_detail_layout).apply {
            animate().translationY(0f)
                .setListener(null)
        }

        findViewById<ImageView>(R.id.finish_trade_price_more_triangle).apply {
            animate().rotation(180f)
                .setListener(null)
        }
    }

    override fun showPriceDetail() {
        findViewById<ConstraintLayout>(R.id.finish_trade_price_detail_layout).apply {
            animate().translationY(-height.toFloat())
                .setListener(null)
        }

        findViewById<ImageView>(R.id.finish_trade_price_more_triangle).apply {
            animate().rotation(0f)
                .setListener(null)
        }
    }

    //endregion
}