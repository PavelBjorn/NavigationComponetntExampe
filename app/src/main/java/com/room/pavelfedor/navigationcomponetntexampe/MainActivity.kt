package com.room.pavelfedor.navigationcomponetntexampe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav = supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment

        findViewById<View>(R.id.btSwitchFragment).setOnClickListener {
            nav.navController.apply {
                when (currentDestination.id) {
                    R.id.firstFragment -> navigate(R.id.actionToSecondFragment)
                    R.id.secondFragment -> popBackStack()
                }
            }
        }
    }
}
