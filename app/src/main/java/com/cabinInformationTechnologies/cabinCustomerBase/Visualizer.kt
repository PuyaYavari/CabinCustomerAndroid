package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.ImageSizes
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELImage
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct

class Visualizer : BaseContracts.Visualizer {

    override fun plaineImageVisualizer(
        context: Context,
        modelImage: MODELImage,
        imageView: ImageView
    ) {
        val imageUrl = if (modelImage.getExtension() != null) { //TODO: extension must be non nullable
            modelImage.getURL() + ImageSizes.M + ".${modelImage.getExtension()}"
        } else {
            modelImage.getURL() + ImageSizes.M
        }
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)
    }

    override fun productImageVisualizer(
        context: Context,
        product: MODELProduct,
        imageView: ImageView
    ) {
        var isPriortyFound = false

        //Using "run" and a tag will prevent the loop from running again.
        run loop@{
            product?.getColors()?.forEach { color ->
                color.images.forEach { image ->
                    if (image.getPriority()) { //show priorty image
                        isPriortyFound = true
                        plaineImageVisualizer(context, image, imageView)
                        return@loop //Return to loop and exit when it finds the first priorty image
                    }
                }
            }
        }

        if (!isPriortyFound) { //Show first color's first image
            val image = product.getColors()[0].images[0]
            plaineImageVisualizer(context, image, imageView)
        }
    }

}