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
import com.room.pavelfedor.navigationcomponetntexampe.R


class ViewNavHost : FrameLayout, NavHost {

    private var navController: NavController? = null
    private var stack: ViewStack = ViewStack()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        initAttrs(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private fun initAttrs(context: Context?, attrs: AttributeSet?) {
        val typedArray = context?.resources?.obtainAttributes(attrs, R.styleable.ViewNavHost)
        val graph = typedArray?.getResourceId(R.styleable.ViewNavHost_navigation_graph, -1) ?: -1
        typedArray?.recycle()
        navController = NavController(this.context)
        navController?.saveState()
        navController?.navigatorProvider?.addNavigator(ViewNavigator(this, stack))
        navController?.setGraph(graph)
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState(), stack, navController)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? SavedState)?.apply {
            stack.restoreStack(this.stackState)
            navController?.restoreState(this.navControllerState)
            navController?.navigatorProvider?.getNavigator(ViewNavigator::class.java)?.restoreState(
                    stack, navController?.currentDestination ?: return
            )
            super.onRestoreInstanceState(this.superState)
        }
    }

    override fun getNavController(): NavController {
        return navController ?: throw IllegalArgumentException("NavController can't be null")
    }

    class SavedState : View.BaseSavedState {
        var state: Int = 0
        var stackState: Bundle = Bundle().apply { putParcelableArrayList("stack", ArrayList()) }
            private set

        var navControllerState: Bundle = Bundle()
            private set

        constructor(superState: Parcelable, stackSaved: ViewStack, navControllerState: NavController?) : super(superState) {
            stackState = stackSaved.saveStack()
            this.navControllerState = navControllerState?.saveState() ?: Bundle()
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
            override fun createFromParcel(`in`: Parcel) = SavedState(`in`)

            override fun newArray(size: Int): Array<SavedState?>? = arrayOfNulls(size)
        }
    }
}

