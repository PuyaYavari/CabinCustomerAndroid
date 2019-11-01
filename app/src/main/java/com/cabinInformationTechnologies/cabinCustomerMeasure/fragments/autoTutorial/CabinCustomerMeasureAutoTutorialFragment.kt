package com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.autoTutorial

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.constraintlayout.motion.widget.MotionLayout
import com.cabinInformationTechnologies.cabin.R

@Suppress("DEPRECATION")
class CabinCustomerMeasureAutoTutorialFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerMeasureAutoTutorialContracts.View {

    var presenter: CabinCustomerMeasureAutoTutorialContracts.Presenter? =
        CabinCustomerMeasureAutoTutorialPresenter(
            this
        )
    private lateinit var pageView: View

    private lateinit var callback: OnBackPressedCallback

    private lateinit var headerTransitionContainer: MotionLayout
    private lateinit var imagesTransitionContainer: MotionLayout
    private lateinit var textsTransitionContainer: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_measure_auto_tutorial, container, false)

        // This callback will only be called when MyFragment is at least Started.
        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            presenter?.showPrevious()
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

        headerTransitionContainer = pageView.findViewById(R.id.measure_auto_tutorial_header_layout)
        headerTransitionContainer.setTransition(R.id.measureAutoTutorialHeaderBeforePageLoaded, R.id.measureAutoTutorialHeaderAfterPageLoaded)
        headerTransitionContainer.transitionToEnd()

        imagesTransitionContainer = pageView.findViewById(R.id.measure_auto_tutorial_images_layout)
        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialNoImage, R.id.measureAutoTutorialHairStyleImageVisible)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer = pageView.findViewById(R.id.measure_auto_tutorial_texts_layout)
        textsTransitionContainer.setTransition(R.id.measureAutoTutorialNoText, R.id.measureAutoTutorialHairstyleText)
        textsTransitionContainer.transitionToEnd()

        pageView.findViewById<Button>(R.id.measure_auto_tutorial_next_button)
            .setOnClickListener{ presenter?.showNext() }
    }

    override fun forwardToOutfit() {
        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialHairStyleImageVisible, R.id.measureAutoTutorialOutfitImageVisible)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer.setTransition(R.id.measureAutoTutorialHairstyleText, R.id.measureAutoTutorialOutfitText)
        textsTransitionContainer.transitionToEnd()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
        } else {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
    }

    override fun forwardToShoes() {
        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialOutfitImageVisible, R.id.measureAutoTutorialShoesImageVisible)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer.setTransition(R.id.measureAutoTutorialOutfitText, R.id.measureAutoTutorialShoesText)
        textsTransitionContainer.transitionToEnd()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark, activity!!.theme))
        } else {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        }
    }

    override fun backToHairstyle() {
        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialOutfitImageVisible, R.id.measureAutoTutorialHairStyleImageVisible)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer.setTransition(R.id.measureAutoTutorialOutfitText, R.id.measureAutoTutorialHairstyleText)
        textsTransitionContainer.transitionToEnd()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
        } else {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
    }

    override fun backToOutfit() {
        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialShoesImageVisible, R.id.measureAutoTutorialOutfitImageVisible)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer.setTransition(R.id.measureAutoTutorialShoesText, R.id.measureAutoTutorialOutfitText)
        textsTransitionContainer.transitionToEnd()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark, activity!!.theme))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent, activity!!.theme))
        } else {
            pageView.findViewById<View>(R.id.measure_auto_tutorial_hairstyle_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_outfit_indicator).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
            pageView.findViewById<View>(R.id.measure_auto_tutorial_shoes_indicator).setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
    }

    override fun toPreviousPage() {
        headerTransitionContainer.setTransition(R.id.measureAutoTutorialHeaderAfterPageLoaded, R.id.measureAutoTutorialHeaderBeforePageLoaded)
        headerTransitionContainer.transitionToEnd()

        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialHairStyleImageVisible, R.id.measureAutoTutorialNoImage)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer.setTransition(R.id.measureAutoTutorialHairstyleText, R.id.measureAutoTutorialNoText)
        textsTransitionContainer.transitionToEnd()

        callback.remove()
        activity!!.onBackPressed()
    }

    override fun toNextPage() {
        headerTransitionContainer.setTransition(R.id.measureAutoTutorialHeaderAfterPageLoaded, R.id.measureAutoTutorialHeaderBeforePageLoaded)
        headerTransitionContainer.transitionToEnd()

        imagesTransitionContainer.setTransition(R.id.measureAutoTutorialShoesImageVisible, R.id.measureAutoTutorialNoImage)
        imagesTransitionContainer.transitionToEnd()

        textsTransitionContainer.setTransition(R.id.measureAutoTutorialShoesText, R.id.measureAutoTutorialNoText)
        textsTransitionContainer.transitionToEnd()
    }

    //endregion
}