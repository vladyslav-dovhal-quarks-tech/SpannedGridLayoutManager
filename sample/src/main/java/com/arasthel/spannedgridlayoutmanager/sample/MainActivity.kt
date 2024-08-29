package com.arasthel.spannedgridlayoutmanager.sample

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.arasthel.spannedgridlayoutmanager.SpanSize
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager

/**
 * Created by Jorge Martín on 24/5/17.
 */
class MainActivity : AppCompatActivity() {

  val recyclerview: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

  override fun onCreate(savedInstanceState: android.os.Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val spannedGridLayoutManager = SpannedGridLayoutManager(
      context = this,
      orient = RecyclerView.VERTICAL,
      spans = 3,
      ratio = 1f
    )
    spannedGridLayoutManager.spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
      if (position == 0) {
        SpanSize(2, 2)
      } else {
        SpanSize(1, 1)
      }
    }
    recyclerview.layoutManager = spannedGridLayoutManager

    val adapter = GridItemAdapter()

    if (savedInstanceState != null && savedInstanceState.containsKey("clicked")) {
      val clicked = savedInstanceState.getBooleanArray("clicked")!!
      adapter.clickedItems.clear()
      adapter.clickedItems.addAll(clicked.toList())
    }

    recyclerview.adapter = adapter
  }

  //  override fun onSaveInstanceState(outState: Bundle?) {
  //    super.onSaveInstanceState(outState)
  //
  //    outState?.putBooleanArray("clicked", (recyclerview.adapter as GridItemAdapter).clickedItems.toBooleanArray())
  //  }

}