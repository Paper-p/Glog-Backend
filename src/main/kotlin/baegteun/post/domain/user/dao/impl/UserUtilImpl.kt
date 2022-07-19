package baegteun.post.domain.user.dao.impl

import baegteun.post.domain.user.dao.UserUtil
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.domain.user.domain.repository.UserRepository
import baegteun.post.domain.user.exception.AlreadyExistNicknameException
import baegteun.post.domain.user.exception.AlreadyExistUserIdException
import baegteun.post.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserUtilImpl(
    private val userRepository: UserRepository
): UserUtil {
    override fun fetchCurrentUser(): User {
        val userId = SecurityContextHolder.getContext().authentication.name
        return fetchUserByUserId(userId)
    }

    override fun fetchUserByUserId(userId: String): User =
        userRepository.findByUserId(userId) ?: throw UserNotFoundException.EXCEPTION

    override fun fetchUserById(id: Long): User =
        userRepository.findById(id).orElseThrow { UserNotFoundException.EXCEPTION }

    override fun checkExistsUserId(userId: String) {
        if (userRepository.existsByUserId(userId))
            throw AlreadyExistUserIdException.EXCEPTION
    }

    override fun checkExistsNickname(nickname: String) {
        if (userRepository.existsByNickname(nickname))
            throw AlreadyExistNicknameException.EXCEPTION
    }
}
