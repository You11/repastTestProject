package ru.you11.repasttestproject.main

import ru.you11.repasttestproject.BasePresenter
import ru.you11.repasttestproject.BaseView

interface NearbyContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}

interface FavoriteContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}

interface UserProfileContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter
}