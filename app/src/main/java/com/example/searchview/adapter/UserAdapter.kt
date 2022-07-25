package com.example.searchview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchview.data.User
import com.example.searchview.data.filteredList
import com.example.searchview.data.listOfUsers
import com.example.searchview.databinding.SingleItemBinding
import com.example.searchview.diffUtil.UserDiffCallback

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(SingleItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), mListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    inner class UserViewHolder(
        private val binding: SingleItemBinding,
        listener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            val user = getItem(adapterPosition)
            binding.ivImage.setImageResource(user.image)
            itemView.setOnClickListener {
                mListener.onItemClick(user)
            }
        }
    }

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: User)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                filteredList.clear()
                if (constraint.isNullOrBlank()) {
                    filteredList.addAll(listOfUsers)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim { it <= ' ' }
                    for (item in 0..listOfUsers.size) {
                        if (listOfUsers[item].name!!.lowercase().contains(filterPattern)) {
                            filteredList.add(listOfUsers[item])
                        }
                    }
                }
                return filterResults.also {
                    it.values = filteredList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (filteredList.isNullOrEmpty())
                    notifyDataSetChanged()

            }


        }
    }
}