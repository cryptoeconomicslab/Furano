package com.cryptoeconomicslab.furano_android.db

import android.content.Context

class ExternalStorageResolverImpl(private val context: Context) : ExternalStorageResolver {
    override fun getFilePath(): String = context.filesDir.absolutePath
}