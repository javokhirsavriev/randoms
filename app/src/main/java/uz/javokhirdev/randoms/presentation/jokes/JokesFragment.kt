package uz.javokhirdev.randoms.presentation.jokes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.javokhirdev.randoms.R
import uz.javokhirdev.randoms.core.extensions.grid
import uz.javokhirdev.randoms.core.extensions.repeatingJobOnStarted
import uz.javokhirdev.randoms.core.extensions.start
import uz.javokhirdev.randoms.core.extensions.viewBinding
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.databinding.FragmentJokesBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListPhotoAdapter
import uz.javokhirdev.randoms.presentation.joke.JokeActivity
import uz.javokhirdev.randoms.presentation.navigation.NavArguments

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes), ListItemListener {

    private val binding by viewBinding(FragmentJokesBinding::bind)

    private val viewModel by viewModels<JokesViewModel>()

    private val jokesAdapter by lazy { ListPhotoAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvJokes.grid().adapter = jokesAdapter
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { jokesAdapter.submitList(it) }
        }
    }

    override fun onItemClick(model: ListModel) {
        NavArguments.listModel = model

        start(JokeActivity::class.java)
    }
}