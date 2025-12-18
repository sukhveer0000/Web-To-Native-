package com.kabir.webview.retrofit

import com.kabir.webview.room.WebPage
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebpageApiService {

    @POST("history")
    suspend fun uploadHistory(@Body historyList: List<WebPage>): Response<ResponseBody>
}