package uz.javokhirdev.randoms.presentation.adapters

import uz.javokhirdev.randoms.data.model.ListModel

interface ListItemListener {
    fun onItemClick(model: ListModel)
}