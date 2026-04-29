package com.example.build_config

import javax.inject.Inject

class BuildConfigProvider @Inject constructor() {
    fun getBaseUrl(): String = BuildConfig.BACKEND_URL
}