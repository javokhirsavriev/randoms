package uz.javokhirdev.randoms.presentation.facts

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
import uz.javokhirdev.randoms.databinding.FragmentFactsBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListPhotoAdapter

@AndroidEntryPoint
class FactsFragment : Fragment(R.layout.fragment_facts), ListItemListener {

    private val binding by viewBinding(FragmentFactsBinding::bind)

    private val viewModel by viewModels<FactsViewModel>()

    private val factsAdapter by lazy { ListPhotoAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvFacts.grid().adapter = factsAdapter
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { factsAdapter.submitList(it) }
        }
    }

    override fun onItemClick(model: ListModel) {
    }
}