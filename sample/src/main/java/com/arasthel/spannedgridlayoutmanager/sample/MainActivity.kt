package com.arasthel.spannedgridlayoutmanager.sample

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.arasthel.spannedgridlayoutmanager.SpanSize
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager.Orientation.VERTICAL

/**
 * Created by Jorge Martín on 24/5/17.
 */
class MainActivity : android.support.v7.app.AppCompatActivity() {

    val recyclerview: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    val tvGetLayout: TextView by lazy { findViewById<TextView>(R.id.tv_get_layout) }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val spannedGridLayoutManager = SpannedGridLayoutManager(orientation = VERTICAL, spans = 3)
        spannedGridLayoutManager.itemOrderIsStable = true

        recyclerview.layoutManager = spannedGridLayoutManager

        recyclerview.addItemDecoration(SpaceItemDecorator(left = 10, top = 10, right = 10, bottom = 10))

        val adapter = GridItemAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey("clicked")) {
            val clicked = savedInstanceState.getBooleanArray("clicked")!!
            adapter.clickedItems.clear()
            adapter.clickedItems.addAll(clicked.toList())
        }

        spannedGridLayoutManager.spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
            if (position == 0) {
                SpanSize(3, 1, 200f)
            } else {
                when (position % 8) {
                    0, 5 ->
                        SpanSize(2, 2)
                    3, 7 ->
                        SpanSize(3, 2)
                    else ->
                        SpanSize(1, 1)
                }
            }
        }
        recyclerview.adapter = adapter

        tvGetLayout.setOnClickListener {
            val position = spannedGridLayoutManager.firstVisible2x2LayoutPosition
            Toast.makeText(this, position.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBooleanArray("clicked", (recyclerview.adapter as GridItemAdapter).clickedItems.toBooleanArray())

    }

}