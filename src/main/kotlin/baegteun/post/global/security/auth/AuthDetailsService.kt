package baegteun.post.global.security.auth

import baegteun.post.domain.user.domain.repository.UserRepository
import baegteun.post.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class AuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails =
        userRepository.findByUserId(username ?: "")?.let {
            AuthDetails(it)
        } ?: throw UserNotFoundException.EXCEPTION

}