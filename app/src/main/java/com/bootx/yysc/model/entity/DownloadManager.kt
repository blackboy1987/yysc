package com.bootx.yysc.model.entity

import android.util.Log
import com.bootx.yysc.model.service.DownloadService
import com.bootx.yysc.util.UrlUtils
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.io.File

object DownloadManager {

    suspend fun download(token: String,url: String, file: File): Flow<DownloadState> {
        Log.e("saveToFilesaveToFilesaveToFile", "downloadStart", )
        return flow {
            val retrofit = Retrofit.Builder().baseUrl(UrlUtils.getBaseUrl(url)).build()
            val response = retrofit.create(DownloadService::class.java).download(token,url).execute()
            val gson = Gson()
            Log.e("saveToFilesaveToFilesaveToFile", "download: ${gson.toJson(response)}", )
            if (response.isSuccessful) {
                saveToFile(response.body()!!, file) {
                    emit(DownloadState.InProgress(it))
                }
                emit(DownloadState.Success(file))
            } else {
                Log.e("saveToFilesaveToFilesaveToFile", response.toString() )
                emit(DownloadState.Error(response.toString()))
            }
        }.catch {
            Log.e("saveToFilesaveToFilesaveToFile", it.toString() )
            emit(DownloadState.Error(it.toString()))
        }.flowOn(Dispatchers.IO)
    }

    private inline fun saveToFile(responseBody: ResponseBody, file: File, progressListener: (Int) -> Unit) {
        val total = responseBody.contentLength()
        var bytesCopied = 0
        var emittedProgress = 0
        file.outputStream().use { output ->
            val input = responseBody.byteStream()
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var bytes = input.read(buffer)
            while (bytes >= 0) {
                output.write(buffer, 0, bytes)
                bytesCopied += bytes
                bytes = input.read(buffer)
                val progress = (bytesCopied * 100 / total).toInt()
                if (progress - emittedProgress > 0) {
                    progressListener(progress)
                    emittedProgress = progress
                }
            }
        }
    }
}