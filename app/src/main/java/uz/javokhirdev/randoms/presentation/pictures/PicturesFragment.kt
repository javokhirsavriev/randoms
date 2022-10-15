package uz.javokhirdev.randoms.presentation.pictures

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.javokhirdev.randoms.R
import uz.javokhirdev.randoms.core.extensions.grid
import uz.javokhirdev.randoms.core.extensions.repeatingJobOnStarted
import uz.javokhirdev.randoms.core.extensions.start
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.databinding.FragmentListBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListPhotoAdapter
import uz.javokhirdev.randoms.presentation.navigation.NavArguments

@AndroidEntryPoint
class PicturesFragment : Fragment(R.layout.fragment_list), ListItemListener {

    private val binding by viewBinding(FragmentListBinding::bind)

    private val viewModel by viewModels<PicturesViewModel>()

    private val picturesAdapter by lazy { ListPhotoAdapter(requireContext(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPictures()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvList.grid().adapter = picturesAdapter
        }

        repeatingJobOnStarted { viewModel.uiState.collectLatest { picturesAdapter.submitList(it) } }
    }

    override fun onItemClick(model: ListModel) {
        NavArguments.model = model

        start(PictureActivity::class.java)
    }
}