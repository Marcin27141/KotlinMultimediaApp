package com.example.list3

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

class DataRepo {
    lateinit var uri : Uri

    fun getSharedList() : MutableList<DataItem>? {
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        sharedStoreList?.clear()

        val contentResolver: ContentResolver = ctx.contentResolver
        val cursor = contentResolver.query(uri, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            do {
                val thisId = cursor.getLong(idColumn)
                val thisName = cursor.getString(nameColumn)
                val thisContentUri = ContentUris.withAppendedId(uri, thisId)
                val thisUriPath = thisContentUri.toString()
                sharedStoreList?.add(DataItem(thisName, thisUriPath, "No path yet", thisContentUri))
            } while (cursor.moveToNext())
        }

        return sharedStoreList
    }
    fun getAppList(): MutableList<DataItem>? {
        return appStoreList
    }

    companion object {
        private var INSTANCE: DataRepo? = null
        private lateinit var ctx : Context
        var sharedStoreList: MutableList<DataItem>? = null
        var appStoreList: MutableList<DataItem>? = null

        fun getInstance(ctx: Context) : DataRepo {
            if (INSTANCE == null) {
                INSTANCE = DataRepo()
                sharedStoreList = mutableListOf()
                appStoreList = mutableListOf()
                this.ctx = ctx
            }
            return INSTANCE as DataRepo
        }
    }
}