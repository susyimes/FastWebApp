package com.susyimes.fastwebapp

import android.content.Context
import android.util.Log
import com.susyimes.fastwebapp.database.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import io.objectbox.exception.FileCorruptException
import io.objectbox.model.ValidateOnOpenMode


object ObjectBox {
    private var boxStore: BoxStore? = null
    private const val TAG = "ObjectBox"
    fun init(context: Context) {
        val storeBuilder = MyObjectBox.builder()
            .validateOnOpen(ValidateOnOpenMode.WithLeaves) // Additional DB page validation
            .validateOnOpenPageLimit(20)
            .androidContext(context.applicationContext)
        boxStore = try {
            storeBuilder.build()
        } catch (e: FileCorruptException) { // Demonstrate handling issues caused by devices with a broken file system
            Log.w(TAG, "File corrupt, trying previous data snapshot...", e)
            // Retrying requires ObjectBox 2.7.1+
            storeBuilder.usePreviousCommit()
            storeBuilder.build()
        }
        if (BuildConfig.DEBUG) {
            Log.d(
                TAG, String.format(
                    "Using ObjectBox %s (%s)",
                    BoxStore.getVersion(), BoxStore.getVersionNative()
                )
            )
            // Enable Data Browser on debug builds.
            // https://docs.objectbox.io/data-browser
            AndroidObjectBrowser(boxStore).start(context.applicationContext)
        }
    }

    fun get(): BoxStore? {
        return boxStore
    }
}