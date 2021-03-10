package com.study.vhra.rtimes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.vhra.rtimes.R
import com.study.vhra.rtimes.databinding.ActivityMainBinding
import com.study.vhra.rtimes.domain.usecase.TimeRegisterOfDayInfo
import com.study.vhra.rtimes.ui.widget.TimePickerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeContractor.View, TimePickerView.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var viewModel: HomeViewModel

    private val timePickerView: TimePickerView by lazy { TimePickerView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.buttonRegisterTime.setOnClickListener {
            viewModel.onRegisterTimeButtonClick()
        }

        viewModel.getTimeRegisters().observe(this) { resource ->
            if (resource.isLoading()) showLoading() else hideLoading()

            if (resource.isSuccess()) {
                if (resource.data == null || resource.data.isEmpty()) {
                    showEmptyTimeRegisters()
                } else {
                    showTimeRegisters(resource.data)
                }
            } else {
                showErrorMessage(resource.error)
            }
        }

        viewModel.getTimeSetDialog().observe(this) {
            showRegisterTimeDialog()
        }

        viewModel.getStatusMessage().observe(this) { status ->
            when (status) {
                HomeStatus.REGISTERED_SUCCESSFULLY -> showTimeRegisteredSuccessfully()
                HomeStatus.ERROR_FOR_REGISTERING_TIME -> showErrorForRegisteringTime()
                else -> {}
            }
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

    override fun showTimeRegisters(timeRegisters: List<TimeRegisterOfDayInfo>) {
        Log.d("devlog", "showTimeRegisters: $timeRegisters")
    }

    override fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    override fun onTimeSet(hourOfDay: Int, minute: Int) {
        viewModel.onTimeSetOfDay(hourOfDay, minute)
    }

    private fun showEmptyTimeRegisters() {

    }

    private fun showErrorMessage(error: Int?) {

    }
}
