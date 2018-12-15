package ru.you11.repasttestproject.payment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.Utils.setCircularImage
import java.lang.Exception
import android.support.v7.widget.DividerItemDecoration



class FeedbackFragment: Fragment(), FeedbackContract.View {

    override lateinit var presenter: FeedbackContract.Presenter
    private lateinit var recyclerView: RecyclerView
    private val critiqueList = ArrayList<String>()

    private lateinit var ratingTitle: TextView
    private lateinit var ratingQuestion: TextView
    private lateinit var ratingCommentary: EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_feedback, container, false)
        with(root) {
            recyclerView = findViewById(R.id.feedback_rv_variants)
            val layoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = FeedbackCritiqueRVAdapter(critiqueList, presenter)
            recyclerView.isNestedScrollingEnabled = false
            val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
            recyclerView.addItemDecoration(dividerItemDecoration)

            val workerPhoto = findViewById<ImageView>(R.id.feedback_worker_photo)
            Picasso.get()
                .load(presenter.getWorkerPhotoUrl())
                .fit()
                .into(workerPhoto, object: Callback {
                    override fun onSuccess() {
                        setCircularImage(workerPhoto, activity as Context)
                    }

                    override fun onError(e: Exception?) {
                        //TODO: proper handling
                        throw Exception(e?.localizedMessage)
                    }
                })

            val workerName = findViewById<TextView>(R.id.feedback_worker_name)
            workerName.text = presenter.getWorkerName()

            val workerPosition = findViewById<TextView>(R.id.feedback_worker_position)
            workerPosition.text = presenter.getWorkerPosition()

            val restaurantDescription = findViewById<TextView>(R.id.feedback_restaurant_text)
            restaurantDescription.text = presenter.getRestaurantDescription()

            ratingTitle = findViewById(R.id.feedback_rating_title)
            ratingQuestion = findViewById(R.id.feedback_rating_question)
            ratingCommentary = findViewById(R.id.feedback_commentary_edit_text)

            val continueButton = findViewById<Button>(R.id.feedback_continue_button)
            continueButton.setOnClickListener {
                activity?.finish()
            }

            setupStarLayout(root)
        }

        return root
    }

    private fun setupStarLayout(root: View) {
        val starLayout = root.findViewById<LinearLayout>(R.id.feedback_star_layout)
        var rating = 0
        for (i in 0 until starLayout.childCount) {
            val star = starLayout.getChildAt(i) as ImageView
            star.tag = i + 1
            star.setOnClickListener {
                if (rating == 0) {
                    showHiddenCritiqueViews()
                }
                rating = it.tag as Int

                setStars(rating, starLayout)

                if (rating in 1..3) {
                    showNegativeCritique()
                    ratingQuestion.text = resources.getString(R.string.feedback_negative_question)
                } else {
                    showPositiveCritique()
                    ratingQuestion.text = resources.getString(R.string.feedback_positive_question)
                }

                setRatingTitle(rating)
            }
        }
    }

    private fun setRatingTitle(rating: Int) {
        val titlesArray = resources.getStringArray(R.array.feedback_critique_titles)
        ratingTitle.text = titlesArray[rating - 1]
    }

    private fun setStars(rating: Int, starLayout: LinearLayout) {
        for (j in 0 until rating) {
            Picasso.get().load(R.drawable.baseline_star_black_24).into(starLayout.getChildAt(j) as ImageView)
        }

        for (j in rating..4) {
            Picasso.get().load(R.drawable.baseline_star_border_black_24).into(starLayout.getChildAt(j) as ImageView)
        }
    }

    private fun showHiddenCritiqueViews() {
        ratingTitle.visibility = TextView.VISIBLE
        ratingQuestion.visibility = TextView.VISIBLE
        recyclerView.visibility = RecyclerView.VISIBLE
        ratingCommentary.visibility = EditText.VISIBLE
    }

    private fun showPositiveCritique() {
        critiqueList.clear()
        critiqueList.addAll(resources.getStringArray(R.array.feedback_positive_responses))
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun showNegativeCritique() {
        critiqueList.clear()
        critiqueList.addAll(resources.getStringArray(R.array.feedback_negative_responses))
        recyclerView.adapter?.notifyDataSetChanged()
    }


    class FeedbackCritiqueRVAdapter(private val critiqueList: ArrayList<String>,
                                    private val presenter: FeedbackContract.Presenter):
        RecyclerView.Adapter<FeedbackFragment.FeedbackCritiqueRVAdapter.ViewHolder>() {


        class ViewHolder(val layout: RelativeLayout) : RecyclerView.ViewHolder(layout) {
            val textView: TextView = layout.findViewById(R.id.critique_text_view)
            val checkBox: CheckBox = layout.findViewById(R.id.critique_checkbox)
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return FeedbackCritiqueRVAdapter.ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.critique_card, parent, false) as RelativeLayout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = critiqueList[position]
            holder.layout.setOnClickListener {
                if (holder.checkBox.isChecked) {
                    holder.checkBox.visibility = CheckBox.INVISIBLE
                    holder.checkBox.isChecked = false
                } else {
                    holder.checkBox.visibility = CheckBox.VISIBLE
                    holder.checkBox.isChecked = true
                }
            }
        }

        override fun getItemCount(): Int {
            return critiqueList.size
        }
    }
}