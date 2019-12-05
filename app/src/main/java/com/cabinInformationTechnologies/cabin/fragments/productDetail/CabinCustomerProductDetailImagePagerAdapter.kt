package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Visualizer
import com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.ImageSizes
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELImage


class CabinCustomerProductDetailImagePagerAdapter(
    private var modelImageArrayList: ArrayList<MODELImage>,
    private var inflater: LayoutInflater): PagerAdapter() {

    fun setList(newList: List<MODELImage>) {
        modelImageArrayList.clear()
        modelImageArrayList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return modelImageArrayList.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.cabin_customer_image_pager_layout, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image_pager_image_layout) as ImageView

        val visualizer = Visualizer()
        visualizer.plaineImageVisualizer(view.context, modelImageArrayList[position],imageView)

//        val image = modelImageArrayList[position]
//
//        val imageUrl = if (image.getExtension() != null) {
//            image.getURL() + ImageSizes.M + ".${image.getExtension()}"
//        } else {
//            image.getURL() + ImageSizes.M
//        }
//        Glide.with(view.context)
//            .load(imageUrl)
//            .into(imageView)

        //imageView.setImageResource(modelImageArrayList[position])//FIXME: DOWNLOAD IMAGES AND SEND HERE

        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }
}