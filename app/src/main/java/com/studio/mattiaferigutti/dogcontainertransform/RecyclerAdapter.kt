package com.studio.mattiaferigutti.dogcontainertransform

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_dog.view.*
import java.util.*

class RecyclerAdapter(val list: List<Dog>, val clickListener: (View, Dog) -> (Unit)): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(dog: Dog, clickListener: (View, Dog) -> (Unit)) {
            with(containerView) {
                breed_name.text = dog.breed.capitalize(Locale.ROOT)
                dog.photo.let { it1 -> image_thumbnail?.setImageResource(it1) }
                //Set the transition name for each Item Container
                ViewCompat.setTransitionName(item_container, dog.breed)

                item_container.rootView.transitionName = dog.breed
                breed_name.rootView.transitionName = dog.breed
                image_thumbnail.rootView.transitionName = dog.breed

                setOnClickListener { clickListener(item_container, dog) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = list[position]
        holder.bind(dog, clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}