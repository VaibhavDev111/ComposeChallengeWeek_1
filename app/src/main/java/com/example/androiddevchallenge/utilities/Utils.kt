package com.example.androiddevchallenge.utilities

import android.content.Context
import com.example.androiddevchallenge.models.PuppiesData
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset

class Utils {

    fun loadJsonFromAssets(context: Context): PuppiesData? {
        var puppyDetailModel: PuppiesData? = null
        val detailFileName = "detail.json"
        try {
            val inputStream = context.assets.open(detailFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charset.defaultCharset())
            puppyDetailModel = Gson().fromJson(json, PuppiesData::class.java)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return puppyDetailModel
    }

}