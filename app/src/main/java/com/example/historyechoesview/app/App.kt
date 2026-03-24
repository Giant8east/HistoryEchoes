package com.example.historyechoesview.app

import android.app.Application
import com.drake.net.BuildConfig
import com.drake.net.NetConfig
import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.net.request.BaseRequest
import com.example.historyechoesview.network.Api
import com.example.historyechoesview.network.SerializationConverter
import java.util.concurrent.TimeUnit

/**
 * 应用的全局入口
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        NetConfig.initialize(Api.HOST, this) {
            // 超时配置, 默认是10秒, 设置太长时间会导致用户等待过久
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            setDebug(BuildConfig.DEBUG)
            setRequestInterceptor(object : RequestInterceptor {
                override fun interceptor(request: BaseRequest) {
                    request.setHeader("token", "")
                }
            })
            setConverter(SerializationConverter())
        }
    }
}