package baegteun.post.domain.user.utils

import baegteun.post.domain.user.domain.entity.User

interface UserUtil {
    fun fetchCurrentUser(): User
    fun fetchUserByUserId(userId: String): User
    fun fetchUserById(id: Long): User
    fun checkExistsUserId(userId: String)
    fun checkExistsNickname(nickname: String)
    fun existsByUserId(userId: String): Boolean
}