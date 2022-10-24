package com.david0926.foodinfo1021.util

import com.david0926.foodinfo1021.BuildConfig
import java.net.URLDecoder

object ApiKeyUtil {
    fun getApiKey() = URLDecoder.decode(BuildConfig.API_KEY, "utf-8")
}