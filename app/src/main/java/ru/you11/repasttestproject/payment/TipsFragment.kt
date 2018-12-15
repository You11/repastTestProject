package ru.you11.repasttestproject.payment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.model.Worker
import java.lang.Exception
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.graphics.drawable.BitmapDrawable
import ru.you11.repasttestproject.Utils.setCircularImage


class TipsFragment : Fragment(), TipsContract.View {

    override lateinit var presenter: TipsContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private val workers = ArrayList<Worker>()

    private lateinit var rootLayout: RelativeLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_tips, container, false)
        with(root) {
            recyclerView = findViewById(R.id.tips_rv)
            recyclerView.layoutManager = LinearLayoutManager(activity as Context)
            recyclerView.adapter = WorkersRecyclerViewAdapter(workers, presenter)
            recyclerView.isNestedScrollingEnabled = false

            rootLayout = findViewById(R.id.tips_root_relative_layout)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.clearDisposable()
    }

    override fun updateRVWithWorkers(workers: ArrayList<Worker>) {
        this.workers.clear()
        this.workers.addAll(workers)
        recyclerView.adapter?.notifyDataSetChanged()
        rootLayout.visibility = RelativeLayout.VISIBLE
    }

    class WorkersRecyclerViewAdapter(private val results: ArrayList<Worker>,
                                     private val presenter: TipsContract.Presenter):
        RecyclerView.Adapter<WorkersRecyclerViewAdapter.ViewHolder>() {


        class ViewHolder(val layout: RelativeLayout) : RecyclerView.ViewHolder(layout) {
            val name: TextView = layout.findViewById(R.id.rv_worker_name)
            val position: TextView = layout.findViewById(R.id.rv_worker_position)
            val photo: ImageView = layout.findViewById(R.id.rv_worker_photo)
            val likeButton: ImageView = layout.findViewById(R.id.rv_worker_like_button)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkersRecyclerViewAdapter.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.workers_card,
                    parent,
                    false
                ) as RelativeLayout
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.name.context

            val worker = results[position]
            holder.name.text = worker.name
            holder.position.text = worker.position
            loadPhoto(worker.photo, holder.photo, context)

            holder.layout.setOnClickListener {
                presenter.startPaymentFragment(worker)
            }
        }

        override fun getItemCount(): Int {
            return results.size
        }

        private fun loadPhoto(url: String, imageView: ImageView, context: Context) {
            Picasso.get()
                .load(url)
                .fit()
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        setCircularImage(imageView, context)
                    }

                    override fun onError(e: Exception?) {
                        //TODO: should load default avatar, but let's throw exception for now
                        throw Exception(e?.localizedMessage)
                    }
                })
        }
    }
}