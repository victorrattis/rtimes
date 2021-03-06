package com.study.vhra.rtimes.ui.widget

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerView(
    private val listener: OnTimeSetListener,
) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity,
            this,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        listener.onTimeSet(hourOfDay, minute)
    }

    fun show(
        activity: AppCompatActivity, tag: String = "timePicker"
    ) = show(activity.supportFragmentManager, tag)

    fun isShowing(): Boolean = dialog != null && dialog!!.isShowing && !isRemoving

    interface OnTimeSetListener {
        fun onTimeSet(hourOfDay: Int, minute: Int)
    }
}