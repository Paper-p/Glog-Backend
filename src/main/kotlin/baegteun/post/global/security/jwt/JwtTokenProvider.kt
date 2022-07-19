package baegteun.post.global.security.jwt

import baegteun.post.domain.auth.domain.repository.RefreshTokenRepository
import baegteun.post.global.security.auth.AuthDetails
import baegteun.post.global.security.auth.AuthDetailsService
import baegteun.post.global.security.jwt.properties.JwtProperties
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {

}