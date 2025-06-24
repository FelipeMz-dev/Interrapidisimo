package com.felipemz.interrapidsimo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipemz.interrapidsimo.data.db.dao.TableDao
import com.felipemz.interrapidsimo.data.db.dao.UserDao
import com.felipemz.interrapidsimo.data.db.entity.TableEntity
import com.felipemz.interrapidsimo.data.db.entity.UserEntity

@Database(entities = [UserEntity::class, TableEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tableDao(): TableDao
}