package me.fsfaysalcse.tu.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import me.fsfaysalcse.tu.data.database.DATA_STORE_FILE_NAME
import me.fsfaysalcse.tu.data.database.createDataStore

fun createDataStore(context: Context): DataStore<Preferences> {
    return createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}