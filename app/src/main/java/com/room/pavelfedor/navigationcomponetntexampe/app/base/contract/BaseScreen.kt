package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseScreen : Screen {

    private var args: Bundle = Bundle()

    override fun inflateView(context: Context, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(layoutRes, parent, false)
    }

    override fun getState(): Screen.State {
        return Screen.State(layoutRes, Bundle())
    }

    override fun setState(state: Screen.State) {
        layoutRes = state.layoutRes
    }

    override fun setArgs(bundle: Bundle) {
        this.args = bundle
    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }

    override fun onPause() {

    }

    override fun onResume() {
        
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

    }
}
