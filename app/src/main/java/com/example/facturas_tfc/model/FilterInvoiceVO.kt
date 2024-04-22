package com.example.facturas_tfc.model

import android.widget.CheckBox

data class FilterInvoiceVO(

    var maxDate: String,
    var minDate: String,
    var maxValueSlider: Double,
    var status: HashMap<String, Boolean>
)

{}