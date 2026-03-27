package com.example.historyechoesview.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class  HistoryEchoesDetailModel(
    @SerialName("code")
    var code: Int = 0,
    @SerialName("data")
    var `data`: Data = Data(),
    @SerialName("message")
    var message: String = "",
    @SerialName("request_id")
    var requestId: String = "",
    @SerialName("success")
    var success: Boolean = false,
    @SerialName("time")
    var time: Int = 0,
    @SerialName("usage")
    var usage: Int = 0
) {
    @Serializable
    data class Data(
        @SerialName("content")
        var content: String = "",
        @SerialName("date")
        var date: String = "",
        @SerialName("day")
        var day: Int = 0,
        @SerialName("desc")
        var desc: String = "",
        @SerialName("id")
        var id: String = "",
        @SerialName("month")
        var month: Int = 0,
        @SerialName("monthday")
        var monthday: String = "",
        @SerialName("title")
        var title: String = "",
        @SerialName("year")
        var year: Int = 0
    )
}