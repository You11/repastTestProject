package ru.you11.repasttestproject.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.model.Restaurant

class NearbyFragment: Fragment(), NearbyContract.View {

    override lateinit var presenter: NearbyContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_nearby, container, false)
        with(root) {
            val restaurantResults = ArrayList<Restaurant>()
            restaurantResults.add(Restaurant(0, "Maecenas 1903", 5.0, "Екатеринбург, ул. Братьев Быковых, 74", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "http://cookit.zenithapps.ru/images/prt.jpg", "12312", 4))
            restaurantResults.add(Restaurant(0, "Maecenas 1903", 5.0, "Екатеринбург, ул. Братьев Быковых, 74", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "http://cookit.zenithapps.ru/images/prt.jpg", "12312", 4))

            val rv = findViewById<RecyclerView>(R.id.nearby_rv)
            rv.layoutManager = LinearLayoutManager(activity)
            rv.adapter = RestaurantRecyclerViewAdapter(restaurantResults)
            rv.isNestedScrollingEnabled = false
        }

        return root
    }

    class RestaurantRecyclerViewAdapter(private val results: ArrayList<Restaurant>):
        RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {

        class ViewHolder(val layout: CardView): RecyclerView.ViewHolder(layout) {
            val photo: ImageView = layout.findViewById(R.id.rv_restaurant_photo)
            val name: TextView = layout.findViewById(R.id.rv_restaurant_name)
            val starsLayout: LinearLayout = layout.findViewById(R.id.rv_restaurant_stars_layout)
            val rating: TextView = layout.findViewById(R.id.rv_restaurant_rating)
            val address: TextView = layout.findViewById(R.id.rv_restaurant_address)
            val description: TextView = layout.findViewById(R.id.rv_restaurant_description)
            val tipsButton: Button = layout.findViewById(R.id.rv_restaurant_give_tips_button)
            val callButton: Button = layout.findViewById(R.id.rv_restaurant_call_button)
            val favoriteButton: Button = layout.findViewById(R.id.rv_restaurant_favorite_button)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_card, parent, false) as CardView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val restaurant = results[position]
            Picasso.get()
                .load(restaurant.photo)
                .fit()
                .into(holder.photo)
            holder.name.text = restaurant.name
            holder.rating.text = restaurant.rating.toString()
            holder.address.text = restaurant.address
            holder.description.text = restaurant.description
        }

        override fun getItemCount(): Int {
            return results.size
        }
    }
}