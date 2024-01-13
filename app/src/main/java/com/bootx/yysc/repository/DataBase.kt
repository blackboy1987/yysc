package com.bootx.yysc.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bootx.yysc.repository.dao.DownloadManagerDao
import com.bootx.yysc.repository.dao.HistoryDao
import com.bootx.yysc.repository.entity.DownloadManagerEntity
import com.bootx.yysc.repository.entity.HistoryEntity
import com.bootx.yysc.repository.entity.UserEntity

@Database(
    version = 6,
    entities = [
        UserEntity::class,
        HistoryEntity::class,
        DownloadManagerEntity::class,
    ],
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    companion object {
        private var db: DataBase? = null
        private var name = "yysc"
        fun getDb(context: Context) = if (db == null) {
            Room.databaseBuilder(context, DataBase::class.java, name)
                .enableMultiInstanceInvalidation().fallbackToDestructiveMigration().build().apply {
                db = this
            }
        } else {
            db
        }
    }
    abstract fun getHistoryDao(): HistoryDao

    abstract fun getDownloadManagerDao(): DownloadManagerDao

}