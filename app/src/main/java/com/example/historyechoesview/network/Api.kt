package com.example.historyechoesview.network

object Api {
    const val HOST = "https://v3.alapi.cn"

    /**
     * 历史回声列表
     */
    const val HISTORY_ECHOES = "/api/eventHistory"

    /**
     * 历史回声详情
     */
    const val HISTORY_ECHOES_DETAIL = "/api/eventHistory/get"
}