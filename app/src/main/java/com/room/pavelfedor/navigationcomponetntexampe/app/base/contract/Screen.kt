package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation.ScreenDestination
import javax.inject.Inject

class Screen private constructor(
        parent: ViewGroup,
        private var layoutResId: Int,
        var destinationId: Int
) : LifecycleObserver {

    @Inject lateinit var presenter: BasePresenter<*>
    var view: BaseView = LayoutInflater.from(parent.context).inflate(
            layoutResId,
            parent,
            false
    ) as BaseView

    fun saveState() = State(layoutResId, destinationId, view.args)

    private fun restorState(state: State) {
        layoutResId = state.layoutResId
        destinationId = state.destinationId
    }

    fun setArgs(bundle: Bundle) {
        view.args = bundle
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        presenter.attachView(view)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        presenter.dettachView()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

    }

    interface BaseView {
        var args: Bundle
    }

    interface BasePresenter<V : BaseView> {

        var view: V

        fun attachView(view: BaseView)

        fun dettachView()
    }

    open class State(val layoutResId: Int, val destinationId: Int, var args: Bundle) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readBundle()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(layoutResId)
            parcel.writeInt(destinationId)
            parcel.writeBundle(args)
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun equals(other: Any?) = (other as? State)?.run {
            layoutResId == this@State.layoutResId && destinationId == this@State.destinationId
        } ?: false

        override fun hashCode() = destinationId

        companion object CREATOR : Parcelable.Creator<State> {

            override fun createFromParcel(parcel: Parcel) = State(parcel)

            override fun newArray(size: Int): Array<State?> = arrayOfNulls(size)

            fun from(destination: ScreenDestination, args: Bundle) = State(
                    layoutResId = destination.layoutResId,
                    destinationId = destination.id,
                    args = args
            )
        }
    }

    companion object {
        fun newInstanse(parent: ViewGroup, state: Screen.State): Screen {
            return Screen(parent, state.layoutResId, state.destinationId)
        }
    }
}
