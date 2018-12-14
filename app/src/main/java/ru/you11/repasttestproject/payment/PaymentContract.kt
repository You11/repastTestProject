package ru.you11.repasttestproject.payment

import ru.you11.repasttestproject.BasePresenter
import ru.you11.repasttestproject.BaseView


interface TipsContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}

interface PaymentContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}

interface FeedbackContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}