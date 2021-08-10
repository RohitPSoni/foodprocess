package com.example.orders.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol.HTTP_1_0
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class FileInterceptor(private val context: Context): Interceptor {

    override fun intercept(chain: Chain): Response {
        val url = chain.request().url
        val response = if (url.toString().contains("orders.json")) {
            context.assets.open("orders.json")
        } else {
            context.assets.open("ingridients.json")
        }

        val size: Int = response.available()
        val buffer = ByteArray(size)
        response.read(buffer)
        response.close()
        val result = String(buffer)
        return Response.Builder()
            .message(result)
            .code(200)
            .request(chain.request())
            .protocol(HTTP_1_0)
            .body(result.toResponseBody("application/json".toMediaTypeOrNull()))
            .build()
    }
}