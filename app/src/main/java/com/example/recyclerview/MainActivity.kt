package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.dto.Summoner
import com.example.recyclerview.network.RiotService
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response =
            RiotService.getService().requestSummonerInfo(summonerName = "jjuncoder")
                .enqueue(object : Callback<Summoner> {
                    override fun onFailure(call: Call<Summoner>, t: Throwable) {
                        Log.d("debug", "FAIL")
                        println("FAIL")
                    }

                    override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) {
                        if (response.isSuccessful) {
                            val data: Summoner? = response.body()
                            Log.d("debug", data?.summonerLevel.toString())
                        }
                    }

                })

        recyclerView.adapter = MyRecyclerViewAdapter()
        var linearManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearManager
    }

    class MyRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val HEADER_IMAGE = 0
        private val GENERAL_IMAGE = 1

        override fun getItemViewType(position: Int): Int {
            if (position == 0) return HEADER_IMAGE
            return GENERAL_IMAGE
        }

        val images =
            arrayOf(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4
            )

        // Connect : view - layout  by type
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

            var params = view.layoutParams
            params.height = parent.measuredWidth / 3
            params.width = parent.measuredWidth

            return MyViewHolder(view)
        }

        // Count item
        override fun getItemCount(): Int {
            return images.size
        }

        // Bind view holder - data
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var view = holder as MyViewHolder
            view!!.profile_imageView!!.setImageResource(images[position])
            view!!.title_textView!!.text = "Title ${position.toString()}"
            view!!.content_textView!!.text = "CONTENT ${position.toString()}"
        }
    }
}

class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    var profile_imageView: ImageView? = null
    var title_textView: TextView? = null
    var content_textView: TextView? = null

    init {
        profile_imageView = view!!.findViewById(R.id.profile_imageview)
        title_textView = view!!.findViewById(R.id.title_textview)
        content_textView = view!!.findViewById(R.id.content_textview)
    }
}

