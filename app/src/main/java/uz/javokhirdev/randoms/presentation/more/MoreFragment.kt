package uz.javokhirdev.randoms.presentation.more

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.javokhirdev.randoms.R
import uz.javokhirdev.randoms.core.extensions.repeatingJobOnStarted
import uz.javokhirdev.randoms.core.extensions.vertical
import uz.javokhirdev.randoms.core.extensions.viewBinding
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.databinding.FragmentMoreBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListTitleAdapter

@AndroidEntryPoint
class MoreFragment : Fragment(R.layout.fragment_more), ListItemListener {

    private val binding by viewBinding(FragmentMoreBinding::bind)

    private val viewModel by viewModels<MoreViewModel>()

    private val moreAdapter by lazy { ListTitleAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvMore.vertical().adapter = moreAdapter
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { moreAdapter.submitList(it) }
        }
    }

    override fun onItemClick(model: ListModel) {
    }
}