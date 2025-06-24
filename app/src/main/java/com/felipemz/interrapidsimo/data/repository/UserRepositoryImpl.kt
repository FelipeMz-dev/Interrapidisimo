package com.felipemz.interrapidsimo.data.repository

import com.felipemz.interrapidsimo.data.db.dao.UserDao
import com.felipemz.interrapidsimo.data.db.entity.UserEntity
import com.felipemz.interrapidsimo.data.db.entity.toDomainModel
import com.felipemz.interrapidsimo.domain.model.UserAccount
import com.felipemz.interrapidsimo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {

    override suspend fun saveUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun getUser(): UserAccount? {
        return dao.getLoggedUser()?.toDomainModel()
    }

    override suspend fun deleteUser() {
        dao.deleteUser()
    }
}