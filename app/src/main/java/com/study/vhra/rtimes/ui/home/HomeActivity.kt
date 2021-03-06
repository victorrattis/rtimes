package com.study.vhra.rtimes.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.vhra.rtimes.R
import com.study.vhra.rtimes.databinding.ActivityMainBinding
import com.study.vhra.rtimes.ui.widget.TimePickerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeContractor.View, TimePickerView.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var presenter: HomeContractor.Presenter

    private val timePickerView: TimePickerView by lazy { TimePickerView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.buttonRegisterTime.setOnClickListener {
            presenter.onRegisterTimeButton()
        }
    }

    override fun showRegisterTimeDialog() {
        if (!timePickerView.isShowing()) {
            timePickerView.show(this)
        }
    }

    override fun showTimeRegisteredSuccessfully() {
        Toast.makeText(
            this,
            getString(R.string.text_time_resgistered_successfully),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showErrorForRegisteringTime() {
        Toast.makeText(
            this,
            getString(R.string.text_time_unregistered_error),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onTimeSet(hourOfDay: Int, minute: Int) {
        presenter.onTimeSetOfDay(hourOfDay, minute)
    }
}
