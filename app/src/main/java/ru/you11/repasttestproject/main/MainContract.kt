package ru.you11.repasttestproject.main

import ru.you11.repasttestproject.BasePresenter
import ru.you11.repasttestproject.BaseView
import ru.you11.repasttestproject.model.Restaurant

interface NearbyContract {

    interface View: BaseView<Presenter> {

        fun updateRVWithRestaurants(restaurants: ArrayList<Restaurant>)
    }

    interface Presenter: BasePresenter {

        fun loadRestaurants()

        fun startPaymentActivity(restaurantId: Int)
    }
}

interface FavoriteContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter
}

interface UserProfileContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter
}