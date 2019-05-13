package com.aochdjp.playwithgura_san.model.util

import android.app.DatePickerDialog
import android.content.Context
import com.aochdjp.playwithgura_san.R
import java.util.Calendar

class DatePick {
    companion object {
        private var currentYear = -1
        private var currentMonth = -1
        private var currentDay = -1
        private var arrangedDate: String? = ""

        var listener: Listener? = null

        fun openDatePicker(currentValue: String?, context: Context?) {

            arrangedDate = currentValue!!
            val dateList: List<String>? = dateSplit(arrangedDate)
            val defaultYear: Int
            val defaultMonth: Int
            val defaultDay: Int

            if (dateList != null && dateList.isNotEmpty() && arrangedDate != context!!.resources.getString(R.string.common_no_data)) {
                defaultYear = dateList[0].toInt()
                defaultMonth = dateList[1].toInt()
                defaultDay = dateList[2].toInt()
            } else {
                setCurrentDate()

                defaultYear = currentYear
                defaultMonth = currentMonth + 1
                defaultDay = currentDay
            }

            val datePicker = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dateOfMonth ->
                arrangedDate = String.format("%d/%02d/%02d", year, monthOfYear + 1, dateOfMonth)
            }, defaultYear, defaultMonth - 1, defaultDay)

            datePicker.show()
            datePicker.setOnDismissListener {
                listener?.onDismiss(arrangedDate!!)
            }
        }

        private fun setCurrentDate() {
            val calendar = Calendar.getInstance()
            currentYear = calendar.get(Calendar.YEAR)
            currentMonth = calendar.get(Calendar.MONTH)
            currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        }

        private fun dateSplit(realDate: String?): List<String>? = realDate?.split("/")

        interface Listener {
            fun onDismiss(date: String)
        }
    }
}