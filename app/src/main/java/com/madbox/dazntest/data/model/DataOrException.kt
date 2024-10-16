package com.madbox.dazntest.data.model

data class DataOrException<T,E:Exception>(
    var data : T? = null,
    var e: E? = null
)
