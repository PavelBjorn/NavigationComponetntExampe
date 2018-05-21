package com.room.pavelfedor.navigationcomponetntexampe

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class TestView : View{
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onSaveInstanceState(): Parcelable {
        return super.onSaveInstanceState()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }



}
