package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.room.pavelfedor.navigationcomponetntexampe.R
import kotlinx.android.synthetic.main.activity_main.*

class MainNavigationActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        navContainer.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        navContainer.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
