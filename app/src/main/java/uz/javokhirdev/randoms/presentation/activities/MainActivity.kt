package uz.javokhirdev.randoms.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.javokhirdev.randoms.R
import uz.javokhirdev.randoms.databinding.ActivityMainBinding
import uz.javokhirdev.randoms.presentation.fragments.FactsFragment
import uz.javokhirdev.randoms.presentation.fragments.JokesFragment
import uz.javokhirdev.randoms.presentation.fragments.MoreFragment
import uz.javokhirdev.randoms.presentation.navigation.NavRoutes
import uz.javokhirdev.randoms.presentation.fragments.PicturesFragment
import uz.javokhirdev.randoms.presentation.fragments.QuotesFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)

            navController.graph = navController.createGraph(
                startDestination = NavRoutes.pictures
            ) {
                fragment<PicturesFragment>(route = NavRoutes.pictures) {
                    label = getString(R.string.title_pictures)
                }
                fragment<JokesFragment>(route = NavRoutes.jokes) {
                    label = getString(R.string.title_memes)
                }
                fragment<FactsFragment>(route = NavRoutes.facts) {
                    label = getString(R.string.title_facts)
                }
                fragment<QuotesFragment>(route = NavRoutes.quotes) {
                    label = getString(R.string.title_quotes)
                }
                fragment<MoreFragment>(route = NavRoutes.more) {
                    label = getString(R.string.title_more)
                }
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.route == NavRoutes.pictures) {
                    bottomNav.selectedItemId = R.id.pictures
                }

                toolbar.title = destination.label.toString()
            }

            bottomNav.setupWithNavControllerCustom(navController)
        }
    }

    private fun BottomNavigationView.setupWithNavControllerCustom(
        navController: NavController
    ) {
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .setPopUpTo(
                navController.graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )
            .build()

        setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.pictures -> {
                    navController.navigate(NavRoutes.pictures, options)
                    return@setOnItemSelectedListener true
                }
                R.id.memes -> {
                    navController.navigate(NavRoutes.jokes, options)
                    return@setOnItemSelectedListener true
                }
                R.id.facts -> {
                    navController.navigate(NavRoutes.facts, options)
                    return@setOnItemSelectedListener true
                }
                R.id.quotes -> {
                    navController.navigate(NavRoutes.quotes, options)
                    return@setOnItemSelectedListener true
                }
                R.id.more -> {
                    navController.navigate(NavRoutes.more, options)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}
