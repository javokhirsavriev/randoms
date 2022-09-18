package uz.javokhirdev.randoms.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions

fun Fragment.navigateTo(
    destination: String,
    previousDestination: String? = null
) {
    findNavController().navigateTo(destination, previousDestination)
}

fun Fragment.navigateUp(): Boolean {
    return try {
        findNavController().navigateUp()
    } catch (t: Throwable) {
        false
    }
}

fun NavController.navigateTo(
    destination: String,
    previousDestination: String? = null
) {
    try {
        previousDestination?.let {
            popBackStack(it, true)
        }

        navigate(
            route = destination,
            navOptions = navOptions {
                anim {
                    enter = androidx.navigation.ui.R.animator.nav_default_enter_anim
                    exit = androidx.navigation.ui.R.animator.nav_default_exit_anim
                    popEnter = androidx.navigation.ui.R.animator.nav_default_pop_enter_anim
                    popExit = androidx.navigation.ui.R.animator.nav_default_pop_exit_anim
                }
            }
        )
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}
