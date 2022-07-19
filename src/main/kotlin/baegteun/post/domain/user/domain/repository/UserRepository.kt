package baegteun.post.domain.user.domain.repository

import baegteun.post.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(userId: String): User?
}