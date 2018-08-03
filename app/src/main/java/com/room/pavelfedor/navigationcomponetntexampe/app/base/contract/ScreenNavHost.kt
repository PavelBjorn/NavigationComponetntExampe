package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.content.Context
import android.support.annotation.NavigationRes
import androidx.navigation.NavHost

interface ScreenNavHost : NavHost {

    fun attachScreen(screen: Screen)

    fun detachScreen()

    fun init(context: Context, @NavigationRes navGraphId: Int)
}
