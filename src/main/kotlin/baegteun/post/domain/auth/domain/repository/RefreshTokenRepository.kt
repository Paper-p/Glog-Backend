package baegteun.post.domain.auth.domain.repository

import baegteun.post.domain.auth.domain.entity.RefreshToken
import baegteun.post.domain.user.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {
}