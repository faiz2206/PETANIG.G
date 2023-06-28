package com.example.farmer.uipackage
import com.example.farmer.Model.Farmer
import com.example.farmer.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class FarmerListAdapter(
    private val onItemClickListener: (Farmer) -> Unit,
): ListAdapter<Farmer, FarmerListAdapter.FarmerViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        return FarmerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onItemClickListener(current)
        }
    }


    class FarmerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val farmerTitle = itemView.findViewById<android.widget.TextView>(R.id.titleTextView)
        private val farmerCategory = itemView.findViewById<android.widget.TextView>(R.id.categoryTextView)
    //    private val farmerContent = itemView.findViewById<android.widget.TextView>(R.id.contentTextView)
        fun bind(current: Farmer?) {
            farmerTitle.text = current?.name
            farmerCategory.text = current?.address
        }

        companion object {
            fun create(parent: ViewGroup): FarmerListAdapter.FarmerViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_farmer, parent, false)
                return FarmerViewHolder(view)
            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Farmer>() {
            override fun areItemsTheSame(oldItem: Farmer, newItem: Farmer): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Farmer, newItem: Farmer): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}