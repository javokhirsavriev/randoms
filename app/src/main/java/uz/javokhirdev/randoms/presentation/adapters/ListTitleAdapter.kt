package uz.javokhirdev.randoms.presentation.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.javokhirdev.randoms.core.extensions.inflater
import uz.javokhirdev.randoms.core.extensions.onClick
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.databinding.ItemTitleBinding

class ListTitleAdapter(
    context: Context,
    private val listener: ListItemListener
) : ListAdapter<ListModel, ListTitleAdapter.ViewHolder>(DiffCallback()) {

    private val inflater = context.inflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTitleBinding.inflate(inflater, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                root.onClick { listener.onItemClick(getItem(layoutPosition)) }
            }
        }

        fun bind(item: ListModel) {
            with(binding) {
                textTitle.text = item.title.orEmpty()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ListModel>() {
        override fun areItemsTheSame(
            oldItem: ListModel,
            newItem: ListModel
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ListModel,
            newItem: ListModel
        ): Boolean = oldItem == newItem
    }
}