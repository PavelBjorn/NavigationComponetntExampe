package com.room.pavelfedor.navigationcomponetntexampe

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.room.pavelfedor.navigationcomponetntexampe.navigation.BaseView

class ConstraintView : ConstraintLayout, BaseView {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setArgs(args: Bundle?) {

    }

    override fun getArgs(): Bundle {
        return Bundle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        (context as Activity).window.statusBarColor = (background as ColorDrawable).color
    }
}
