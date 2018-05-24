package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.support.annotation.NavigationRes
import androidx.navigation.NavGraph

interface DIScreenFactory {
    fun inject(screen: Screen)
}

interface DIGraphFactory {
    fun get(@NavigationRes navGraph: Int): DIScreenFactory
}

interface Injector<O> {
    fun inject(obj: O)
}
