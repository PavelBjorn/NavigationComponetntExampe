package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.content.Context
import android.util.AttributeSet
import androidx.navigation.NavDestination
import androidx.navigation.Navigator
import com.room.pavelfedor.navigationcomponetntexampe.R
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen

class ScreenDestination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

    var screen: Screen? = null

    override fun onInflate(context: Context, attrs: AttributeSet) {
        super.onInflate(context, attrs)
        context.resources.obtainAttributes(attrs, R.styleable.ScreenDestination).apply {
            screen = Screen.newInstance(getString(R.styleable.ScreenDestination_screenClassName), defaultArguments)
        }.recycle()
    }
}
