package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen

class ScreenNavigator(val navHost: ScreenNavHost) : Navigator<ScreenDestination>() {

    private val stack: ScreenStack = ScreenStack()

    override fun navigate(destination: ScreenDestination, args: Bundle?, navOptions: NavOptions?) {
        navigate(Screen.State.from(destination, args ?: Bundle()))
    }

    private fun navigate(state: Screen.State) {

        if (state != stack.peek()) {
            navHost.getScreenState()?.apply { stack.updateScreenState(this) }
            stack.put(state)
        }

        navHost.setScreen(Screen.newInstanse(navHost, state))
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
