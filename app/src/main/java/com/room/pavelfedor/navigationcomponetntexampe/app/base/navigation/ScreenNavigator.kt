package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.BaseScreen

class ScreenNavigator(val navHost: ScreenNavHost) : Navigator<ScreenDestination>() {

    private val stack: ScreenStack = ScreenStack()

    override fun navigate(destination: ScreenDestination, args: Bundle?, navOptions: NavOptions?) {

        BaseScreen.State(
                destinationId = destination.id,
                layoutResId = destination.layoutResId,
                args = args ?: Bundle()
        ).apply {
            if (this != stack.peek()) {
                stack.updateScreenState(navHost.getScreenState() ?: return)
                stack.put(this)
            }
        }
    }

    override fun popBackStack(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createDestination() = ScreenDestination(this)

    fun restoreState(state: Bundle) {
        stack.restoreStack(state)
    }

    fun saveState() = stack.saveStack()
}
