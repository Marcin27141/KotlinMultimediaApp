package com.example.list3

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File

class DataRepo {
    lateinit var uri : Uri

    private var photo_storage = SHARED_STORAGE_ID

    fun setStorage(storage: Int) : Boolean {
        if (storage != SHARED_STORAGE_ID && storage != APP_STORAGE_ID)
            return false
        else photo_storage = storage
        return true
    }

    fun getStorage(): Int = photo_storage

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
        val dir: File? = ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        appStoreList?.clear()

        if (dir?.isDirectory == true) {
            var fileList = dir.listFiles()
            if (fileList != null) {
                for (file in fileList) {
                    var fileName = file.name
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
                        || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                        val tmpUri = FileProvider.getUriForFile(ctx, ctx.packageName + ".provider", file)
                        appStoreList?.add(DataItem(fileName, file.toURI().path, file.absolutePath, tmpUri))
                    }
                }
            }
        }
        return appStoreList
    }

    companion object {
        private var INSTANCE: DataRepo? = null
        private lateinit var ctx : Context
        var sharedStoreList: MutableList<DataItem>? = null
        var appStoreList: MutableList<DataItem>? = null

        val SHARED_STORAGE_ID = 0
        val APP_STORAGE_ID = 1

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