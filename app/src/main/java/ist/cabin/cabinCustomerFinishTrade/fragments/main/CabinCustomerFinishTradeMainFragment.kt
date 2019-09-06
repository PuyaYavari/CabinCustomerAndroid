package ist.cabin.cabinCustomerFinishTrade.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerFinishTradeMainFragment : BaseFragment(),
    CabinCustomerFinishTradeMainContracts.View {

    var presenter: CabinCustomerFinishTradeMainContracts.Presenter? =
        CabinCustomerFinishTradeMainPresenter(this)
    private lateinit var pageView: View
    private lateinit var mPager: ViewPager

    private lateinit var callback: OnBackPressedCallback

    private lateinit var transitionContainer: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_finish_trade_main, container, false)

        transitionContainer = pageView.findViewById(R.id.finish_trade_statebar_indicator_layout)

        mPager = pageView.findViewById(R.id.finish_trade_pager)
        val pagerAdapter =
            CabinCustomerFinishTradePagerAdapter(
                childFragmentManager, 0
            )
        mPager.setOnTouchListener { _, _ -> true }
        mPager.adapter = pagerAdapter

        // This callback will only be called when MyFragment is at least Started.
        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            presenter?.pageBackward(mPager.currentItem)
        }

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
        setupFirstPage()

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_first_state_ckeckbox)
            .setOnClickListener { presenter?.pageBackToFirstPage() }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_second_state_ckeckbox)
            .setOnClickListener { presenter?.pageBackward(2) }

        pageView.findViewById<LinearLayout>(R.id.finish_trade_price_inner_layout)
            .setOnClickListener { presenter?.togglePriceDetail() }

    }

    override fun setupFirstPage() {
        pageView.findViewById<Button>(R.id.finish_trade_button).apply {
            setOnClickListener {
                presenter?.pageForward(mPager.currentItem)
            }

            text = resources.getText(R.string.finish_trade_address_select_button_label)
        }

        pageView.findViewById<TextView>(R.id.finish_trade_header_label).text =
            resources.getText(R.string.finish_trade_address_select_header_label)

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_first_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_filled_node)
            isClickable = false
            isEnabled = false
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_second_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_empty_node)
            isClickable = false
            isEnabled = false
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_third_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_empty_node)
            isClickable = false
            isEnabled = false
        }
    }

    override fun setupSecondPage() {
        pageView.findViewById<Button>(R.id.finish_trade_button).apply {
            setOnClickListener {
                presenter?.pageForward(mPager.currentItem)
            }
            text = resources.getText(R.string.finish_trade_payment_button_label)
        }

        pageView.findViewById<TextView>(R.id.finish_trade_header_label).text =
            resources.getText(R.string.finish_trade_payment_header_label)

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_first_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_filled_node)
            isClickable = true
            isEnabled = true
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_second_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_filled_node)
            isClickable = false
            isEnabled = false
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_third_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_empty_node)
            isClickable = false
            isEnabled = false
        }
    }

    override fun setupLastPage() {
        pageView.findViewById<Button>(R.id.finish_trade_button).apply {
            setOnClickListener { presenter?.pageForward(mPager.currentItem) }
            text = resources.getText(R.string.finish_trade_overview_button_label)
        }

        pageView.findViewById<TextView>(R.id.finish_trade_header_label).text =
            resources.getText(R.string.finish_trade_overview_header_label)

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_first_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_filled_node)
            isClickable = true
            isEnabled = true
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_second_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_filled_node)
            isClickable = true
            isEnabled = true
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_trade_third_state_ckeckbox).apply {
            setImageResource(R.drawable.cabin_statebar_filled_node)
            isClickable = false
            isEnabled = false
        }
    }

    override fun hidePriceDetail() {
        pageView.findViewById<ConstraintLayout>(R.id.finish_trade_price_detail_layout).apply {
            animate().translationY(0f)
                .setListener(null)
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_price_more_triangle).apply {
            animate().rotation(180f)
                .setListener(null)
        }
    }

    override fun showPriceDetail() {
        pageView.findViewById<ConstraintLayout>(R.id.finish_trade_price_detail_layout).apply {
            animate().translationY(-height.toFloat())
                .setListener(null)
        }

        pageView.findViewById<ImageView>(R.id.finish_trade_price_more_triangle).apply {
            animate().rotation(0f)
                .setListener(null)
        }
    }

    override fun pageForward() {
        mPager.currentItem += 1
    }

    override fun setPage(page: Int) {
        mPager.currentItem = page
    }

    override fun getPage(): Int {
        return mPager.currentItem
    }

    override fun moveOut() {
        callback.remove()
        activity!!.onBackPressed()
    }

    override fun moveIndicatorTo(fromId: Int, toId: Int) {
        transitionContainer.setTransition(fromId, toId)
        transitionContainer.transitionToEnd()
    }
    //endregion
}