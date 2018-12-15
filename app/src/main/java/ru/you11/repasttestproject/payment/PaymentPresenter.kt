package ru.you11.repasttestproject.payment

import android.content.Context
import ru.yandex.money.android.sdk.*
import ru.you11.repasttestproject.App
import ru.you11.repasttestproject.R
import java.math.BigDecimal
import java.util.*

class PaymentPresenter(private val fragment: PaymentFragment) : PaymentContract.Presenter {

    private val RANDOM_KEY = "213123n1jin3i2bnuhfe"

    init {
        fragment.presenter = this
    }

    override fun start() {

    }

    override fun pay(amount: Double) {
        //test mode, don't delete or place after tokenize
        Checkout.configureTestMode(
            Configuration(
                enableTestMode = true,
                completeWithError = false,
                linkedCardsCount = 1,
                paymentAuthPassed = true
            )
        )

        val paymentTypes = HashSet<PaymentMethodType>()
        paymentTypes.add(PaymentMethodType.BANK_CARD)

        Checkout.tokenize(
            fragment.activity as Context,
            amount = Amount(BigDecimal(amount), Currency.getInstance("RUB")),
            shopParameters = ShopParameters(
                title = getRestaurantName(),
                subtitle = getWorkerName(),
                clientApplicationKey = RANDOM_KEY,
                paymentMethodTypes = paymentTypes
            )
        )
    }

    private fun getRestaurantName(): String {
        return fragment.arguments?.getString("restaurantName") ?: "Ресторант"
    }

    private fun getWorkerName(): String {
        return fragment.arguments?.getString("workerName") ?: "Официант"
    }

    override fun startFeedbackFragment() {
        val newFragment = FeedbackFragment()
        newFragment.arguments = fragment.arguments
        clearBackStack()
        fragment.activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, newFragment)
            ?.commit()
        FeedbackPresenter(newFragment)
    }

    private fun clearBackStack() {
        val supportFragmentManager = fragment.activity?.supportFragmentManager ?: return
        var i = supportFragmentManager.backStackEntryCount

        while (i > 0) {
            supportFragmentManager.popBackStack()
            i--
        }
    }
}