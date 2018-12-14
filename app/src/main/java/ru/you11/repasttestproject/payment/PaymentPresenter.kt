package ru.you11.repasttestproject.payment

class PaymentPresenter(private val fragment: PaymentFragment): PaymentContract.Presenter {


    init {
        fragment.presenter = this
    }


    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}