package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

interface Screen : LifecycleObserver {

    var layoutRes: Int

    fun inflateView(context: Context, parent: ViewGroup): View

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    fun onDetach()

    fun onAttach()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

    fun getState(): State

    fun setState(state: State)

    fun setArgs(bundle: Bundle)

    companion object {
        fun newInstance(className: String, args: Bundle): Screen {
            return (Class.forName(className).newInstance() as?Screen)?.apply {
                setArgs(args)
            } ?: throw IllegalArgumentException("Class should implement Screen")
        }
    }

    data class State(
            val layoutRes: Int,
            var args: Bundle
    )
}
