package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.content.Context
import android.util.AttributeSet
import androidx.navigation.NavDestination
import androidx.navigation.Navigator

class ScreenDestination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

    var layoutResId: Int = -1
        private set

    override fun onInflate(context: Context, attrs: AttributeSet) {
        super.onInflate(context, attrs)
    }
}
