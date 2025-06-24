package com.felipemz.interrapidsimo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.felipemz.interrapidsimo.domain.model.UserAccount

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: String = "",
    val user: String = "",
    val name: String = ""
)

fun UserEntity.toDomainModel() = UserAccount(
    user = user,
    id = id,
    name = name
)