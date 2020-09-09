package com.studio.mattiaferigutti.dogcontainertransform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.android.synthetic.main.fragment_dog_list.*

class DogListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        val dogList = listOf<Dog>(
            Dog("Dog1", R.drawable.dog1),
            Dog("Dog2", R.drawable.dog2),
            Dog("Dog3", R.drawable.dog3),
            Dog("Dog4", R.drawable.dog4),
            Dog("Dog5", R.drawable.dog5),
            Dog("Dog6", R.drawable.dog6),
            Dog("Dog7", R.drawable.dog7),
        )

        val adapter = RecyclerAdapter(dogList) { view, dog ->
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.motion_duration_small).toLong()
            }

            val extras = FragmentNavigatorExtras(view to dog.breed)

            navigate(DogListFragmentDirections.actionDogListFragmentToDogDetailsFragment(dog.breed, dog.photo), extras)
        }

        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter
    }

    private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)?.let {
            navigate(destination, extraInfo)
        }
    }

}