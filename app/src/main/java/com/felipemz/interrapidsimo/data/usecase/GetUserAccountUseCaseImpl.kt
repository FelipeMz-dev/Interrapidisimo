package com.felipemz.interrapidsimo.data.usecase

import com.felipemz.interrapidsimo.domain.repository.UserRepository
import com.felipemz.interrapidsimo.domain.usecase.GetUserAccountUseCase
import javax.inject.Inject

class GetUserAccountUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserAccountUseCase {

    override suspend fun invoke() = userRepository.getUser()
}
