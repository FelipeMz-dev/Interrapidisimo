package com.felipemz.interrapidsimo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felipemz.interrapidsimo.data.db.entity.TableEntity

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tablas: List<TableEntity>)

    @Query("SELECT * FROM tables")
    suspend fun getAll(): List<TableEntity>
}