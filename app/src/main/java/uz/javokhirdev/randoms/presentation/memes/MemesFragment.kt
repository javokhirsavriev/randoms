package uz.javokhirdev.randoms.presentation.memes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.javokhirdev.randoms.R
import uz.javokhirdev.randoms.core.extensions.grid
import uz.javokhirdev.randoms.core.extensions.repeatingJobOnStarted
import uz.javokhirdev.randoms.core.extensions.viewBinding
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.databinding.FragmentMemesBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListPhotoAdapter

@AndroidEntryPoint
class MemesFragment : Fragment(R.layout.fragment_memes), ListItemListener {

    private val binding by viewBinding(FragmentMemesBinding::bind)

    private val viewModel by viewModels<MemesViewModel>()

    private val memesAdapter by lazy { ListPhotoAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvMemes.grid().adapter = memesAdapter
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { memesAdapter.submitList(it) }
        }
    }

    override fun onItemClick(model: ListModel) {
    }
}