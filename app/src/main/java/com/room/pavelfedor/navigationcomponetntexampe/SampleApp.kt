package com.room.pavelfedor.navigationcomponetntexampe

import android.app.Application
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.DIGraphFactory
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.DIGraphFactoryImpl
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.DIScreenFactoryImpl

class SampleApp : Application() {

    private lateinit var dIFactory: DIGraphFactory

    override fun onCreate() {
        super.onCreate()
        dIFactory = DIGraphFactoryImpl.with(listOf(
                R.navigation.nav_graph to DIScreenFactoryImpl.with(
                        listOf(
                                
                        )
                )
        ))
    }

}
