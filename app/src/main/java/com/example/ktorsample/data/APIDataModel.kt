package com.example.ktorsample.data

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class APIDataModel(
    @SerializedName("ResultCode")
    var resultCode: Int = -1,

    @SerializedName("ResultMsg")
    var resultMsg: String? = null,

    @SerializedName("ResultObject")
    var resultObject: JsonElement? = null,
)
