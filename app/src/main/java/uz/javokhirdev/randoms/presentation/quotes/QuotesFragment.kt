package uz.javokhirdev.randoms.presentation.quotes

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
import uz.javokhirdev.randoms.databinding.FragmentQuotesBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListPhotoAdapter

@AndroidEntryPoint
class QuotesFragment : Fragment(R.layout.fragment_quotes), ListItemListener {

    private val binding by viewBinding(FragmentQuotesBinding::bind)

    private val viewModel by viewModels<QuotesViewModel>()

    private val quotesAdapter by lazy { ListPhotoAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvQuotes.grid().adapter = quotesAdapter
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { quotesAdapter.submitList(it) }
        }
    }

    override fun onItemClick(model: ListModel) {
    }
}