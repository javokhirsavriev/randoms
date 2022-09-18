package uz.javokhirdev.randoms.presentation.pictures

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
import uz.javokhirdev.randoms.databinding.FragmentPicturesBinding
import uz.javokhirdev.randoms.presentation.adapters.ListItemListener
import uz.javokhirdev.randoms.presentation.adapters.ListPhotoAdapter
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import uz.javokhirdev.randoms.presentation.picture.PictureActivity

@AndroidEntryPoint
class PicturesFragment : Fragment(R.layout.fragment_pictures), ListItemListener {

    private val binding by viewBinding(FragmentPicturesBinding::bind)

    private val viewModel by viewModels<PicturesViewModel>()

    private val picturesAdapter by lazy { ListPhotoAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvPictures.grid().adapter = picturesAdapter
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { picturesAdapter.submitList(it) }
        }
    }

    override fun onItemClick(model: ListModel) {
        NavArguments.listModel = model

        start(PictureActivity::class.java)
    }
}