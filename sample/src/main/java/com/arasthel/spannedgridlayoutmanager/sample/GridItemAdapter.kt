package com.arasthel.spannedgridlayoutmanager.sample

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Jorge Martín on 24/5/17.
 */
class GridItemAdapter : RecyclerView.Adapter<GridItemViewHolder>() {

  val clickedItems: MutableList<Boolean>

  init {
    clickedItems = MutableList(itemCount, { false })
    setHasStableIds(true)
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.MAGENTA, Color.YELLOW)

  override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
    (holder.itemView as? GridItemView)?.setTitle("$position")

    holder.itemView.setBackgroundColor(
      colors[position % colors.size]
    )

    holder.itemView.setOnClickListener {
      clickedItems[position] = !clickedItems[position]
      notifyItemChanged(position)
    }
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  override fun getItemCount(): Int {
    return 6
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
    val gridItemView = GridItemView(parent.context)

    return GridItemViewHolder(gridItemView)
  }
}

class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)