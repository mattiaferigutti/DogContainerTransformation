package com.studio.mattiaferigutti.dogcontainertransform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.fragment_dog_details.*
import kotlinx.android.synthetic.main.item_dog.view.*

class DogDetailsFragment : Fragment() {

    private val args: DogDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 10000L
            isElevationShadowEnabled = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (breed, image) = args

        image_dog_detail?.setImageResource(image)
        textview_dog_breed.text = breed

        detail_container.transitionName = breed
    }

}