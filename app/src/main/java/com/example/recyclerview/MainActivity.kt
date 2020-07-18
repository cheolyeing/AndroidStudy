package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = MyRecyclerViewAdapter()
        var gridManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = gridManager

        gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0) return 3
                return 1
            }
        }
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
            if(viewType==HEADER_IMAGE) {
                params.height = 2 * parent.measuredWidth / 3
                params.width = parent.measuredWidth
            } else {
                params.height = parent.measuredWidth / 3
                params.width = parent.measuredWidth / 3
            }

            return MyViewHolder(view)
        }

        // Count item
        override fun getItemCount(): Int {
            return images.size
        }

        // Bind view holder - data
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var view = holder as MyViewHolder
            view!!.imageView!!.setImageResource(images[position])
        }
    }
}

class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    var imageView: ImageView? = null

    init {
        imageView = view!!.findViewById(R.id.item_imageView)
    }
}

