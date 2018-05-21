package com.room.pavelfedor.navigationcomponetntexampe.navigation

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@Navigator.Name("view")
class ViewNavigator(private val container: ViewNavHost, private val stack: ViewStack) : Navigator<ViewDestination>() {

    override fun navigate(destination: ViewDestination, args: Bundle?, navOptions: NavOptions?) {
        replaceView(container, ViewState(
                destination.layoutResId,
                args ?: Bundle()
        ))
    }

    override fun createDestination() = ViewDestination(this)

    override fun popBackStack() = stack.popBack()?.run {
        replaceView(container, this)
        true
    } ?: false

    private fun replaceView(container: ViewGroup, view: ViewState) {
        container.getChildAt(0).apply {
            stack.updateState((this as? BaseView)?.getArgs()
                    ?: throw IllegalArgumentException("Should implement BaseView"))
            stack.put(view)
        }

        container.removeAllViews()
        container.addView(view.createView(LayoutInflater.from(container.context), container))
    }

    class ViewState(
            @LayoutRes private var layoutResId: Int = -1,
            private var args: Bundle = Bundle()
    ) : Parcelable {

        constructor(parcel: Parcel) : this(layoutResId = parcel.readInt(),
                args = parcel.readBundle() ?: Bundle())

        fun putArgs(args: Bundle) {
            this.args = args
        }

        fun createView(
                inflater: LayoutInflater,
                parent: ViewGroup
        ) = inflater.inflate(layoutResId, parent, false).apply {
            (this as BaseView).setArgs(args)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(layoutResId)
            parcel.writeBundle(args)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ViewState> {
            override fun createFromParcel(parcel: Parcel): ViewState = ViewState(parcel)
            override fun newArray(size: Int): Array<ViewState?> = arrayOfNulls(size)
        }
    }
}
