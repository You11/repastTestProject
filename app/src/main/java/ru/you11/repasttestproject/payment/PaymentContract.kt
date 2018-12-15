package ru.you11.repasttestproject.payment

import ru.you11.repasttestproject.BasePresenter
import ru.you11.repasttestproject.BaseView
import ru.you11.repasttestproject.model.Worker


interface TipsContract {

    interface View: BaseView<Presenter> {

        fun updateRVWithWorkers(workers: ArrayList<Worker>)
    }

    interface Presenter: BasePresenter {

        fun getRestaurantId(): Int

        fun loadWorkers(restaurantId: Int)

        fun startPaymentFragment(worker: Worker)

        fun clearDisposable()
    }
}

interface PaymentContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

        fun pay(amount: Double)

        fun startFeedbackFragment()
    }
}

interface FeedbackContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}