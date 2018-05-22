package com.room.pavelfedor.navigationcomponetntexampe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.room.pavelfedor.navigationcomponetntexampe.navigation.ViewNavHost

class MainActivity : AppCompatActivity() {

    lateinit var nav: ViewNavHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav = findViewById(R.id.navContainer)

        findViewById<View>(R.id.btSwitchFragment).setOnClickListener {
            nav.navController.apply {
                when (currentDestination?.id) {
                    R.id.firstFragment -> navigate(R.id.actionToSecondFragment)
                    R.id.secondFragment -> popBackStack()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!nav.navController.popBackStack())
            super.onBackPressed()
    }
}
