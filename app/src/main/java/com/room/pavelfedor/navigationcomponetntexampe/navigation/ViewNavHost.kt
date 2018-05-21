package com.room.pavelfedor.navigationcomponetntexampe.navigation

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import android.os.Parcel
import android.view.View


class ViewNavHost : FrameLayout, NavHost {

    private var navController: NavController? = null
    private var stack: ViewStack = ViewStack()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        navController = NavController(context)
        navController?.saveState()
        navController?.navigatorProvider?.addNavigator(ViewNavigator(this, stack))
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState(), stack)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? SavedState)?.apply {
            super.onRestoreInstanceState(this.superState)
            stack.restoreStack(this.stackState)
        }
    }

    override fun getNavController(): NavController {
        return navController ?: throw IllegalArgumentException("NavController can't be null")
    }

    class SavedState : View.BaseSavedState {
        var state: Int = 0
        var stackState: Bundle = Bundle().apply { putParcelableArrayList("stack", ArrayList()) }
        private set

        constructor(superState: Parcelable, stackSaved: ViewStack) : super(superState){
            stackState = stackSaved.saveStack()
        }

        private constructor(`in`: Parcel) : super(`in`) {
            state = `in`.readInt()
            stackState = `in`.readBundle()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(state)
            out.writeBundle(stackState)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(`in`: Parcel): SavedState {
                return SavedState(`in`)
            }

            override fun newArray(size: Int): Array<SavedState?>? {
                return arrayOfNulls(size)
            }
        }
    }
}

