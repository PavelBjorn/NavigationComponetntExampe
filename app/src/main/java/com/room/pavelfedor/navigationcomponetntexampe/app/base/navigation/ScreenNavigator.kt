package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.ScreenNavHost

@Navigator.Name("screen")
class ScreenNavigator(private val navHost: ScreenNavHost) : Navigator<ScreenDestination>() {

    private val stack: ScreenStack = ScreenStack()

    override fun navigate(destination: ScreenDestination, args: Bundle?, navOptions: NavOptions?) {
        navigate(destination.screen ?: return)
    }

    private fun navigate(screen: Screen) {
        screen.apply {
            if (this != stack.peek()) stack.put(this)
            navHost.attachScreen(this)
        }
    }

    override fun popBackStack(): Boolean {
        navHost.attachScreen(stack.popBack())
        return !stack.isEmpty()
    }

    override fun createDestination() = ScreenDestination(this)
}
