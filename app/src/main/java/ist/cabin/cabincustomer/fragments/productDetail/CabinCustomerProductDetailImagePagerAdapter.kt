package ist.cabin.cabincustomer.fragments.productDetail

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import ist.cabin.cabincustomer.R


class CabinCustomerProductDetailImagePagerAdapter(
    private var imageModelArrayList: List<Int>,
    private var inflater: LayoutInflater): PagerAdapter() {


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.cabin_customer_image_pager_layout, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image_pager_image_layout) as ImageView


        imageView.setImageResource(imageModelArrayList[position])//FIXME: DOWNLOAD IMAGES AND SEND HERE

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