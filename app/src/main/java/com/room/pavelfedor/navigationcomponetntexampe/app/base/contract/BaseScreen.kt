package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View

interface BaseScreen<V : BaseScreen.BaseView, P : BaseScreen.BasePresenter<V>> : LifecycleObserver {

    val presenter: P

    val view: View

    val layoutResId: Int

    val destinationId: Int

    fun saveState(): State

    fun restorState(state: State)

    fun onAttach()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    fun onDetach()

    interface BaseView {
        var args: Bundle
    }

    interface BasePresenter<V : BaseView> {

        var view: V

        fun attachView(view: View)

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
        }
    }
}
