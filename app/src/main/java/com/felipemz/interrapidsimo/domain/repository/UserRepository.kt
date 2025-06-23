package com.felipemz.interrapidsimo.domain.repository

import com.felipemz.interrapidsimo.data.db.entity.UserEntity
import com.felipemz.interrapidsimo.domain.model.UserAccount

interface UserRepository {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): UserAccount?
    suspend fun deleteUser()
}