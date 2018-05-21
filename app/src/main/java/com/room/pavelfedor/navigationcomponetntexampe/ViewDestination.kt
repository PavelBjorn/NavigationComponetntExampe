package com.room.pavelfedor.navigationcomponetntexampe

import android.content.Context
import android.util.AttributeSet
import androidx.navigation.NavDestination
import androidx.navigation.Navigator
import androidx.navigation.NavigatorProvider

class ViewDestination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

    var layoutResId: Int = -1
        private set

    constructor(navProvider: NavigatorProvider) : this(navProvider.getNavigator(ViewNavigator::class.java))

    override fun onInflate(context: Context, attrs: AttributeSet) {
        super.onInflate(context, attrs)
        context.resources.obtainAttributes(attrs, R.styleable.ViewNavigator).apply {
            layoutResId = getInt(R.styleable.ViewNavigator_layout, -1)
            recycle()
        }
    }
}
