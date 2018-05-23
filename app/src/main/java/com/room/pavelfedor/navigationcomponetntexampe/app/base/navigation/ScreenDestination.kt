package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import androidx.navigation.NavDestination
import androidx.navigation.Navigator

class ScreenDestination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

    var layoutResId: Int = -1
        private set

}
