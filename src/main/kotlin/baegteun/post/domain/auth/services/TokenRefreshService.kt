package baegteun.post.domain.auth.services

import baegteun.post.domain.auth.domain.repository.RefreshTokenRepository
import baegteun.post.domain.auth.exception.RefreshTokenNotFoundException
import baegteun.post.domain.auth.presentation.dto.response.TokenRefreshResponseDto
import baegteun.post.domain.user.utils.UserUtil
import baegteun.post.domain.user.exception.UserNotFoundException
import baegteun.post.global.security.exception.ExpiredTokenException
import baegteun.post.global.security.jwt.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Objects

@Transactional
@Service
class TokenRefreshService(
    private val userUtil: UserUtil,
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun execute(refreshToken: String): ResponseEntity<TokenRefreshResponseDto> {
        val userId = jwtTokenProvider.exactUserIdFromRefreshToken(refreshToken)
        if (!userUtil.existsByUserId(userId))
            throw UserNotFoundException.EXCEPTION

        val redisRefreshToken = refreshTokenRepository.findById(userId).orElseThrow { RefreshTokenNotFoundException.EXCEPTION }

        val access = jwtTokenProvider.generateAccessToken(userId)
        val refresh = jwtTokenProvider.generateRefreshToken(userId)
        val expiredAt = jwtTokenProvider.getExpiredTime()

        redisRefreshToken.updateToken(refresh, jwtTokenProvider.getRefreshTimeToLive())
        refreshTokenRepository.save(redisRefreshToken)

        val response = TokenRefreshResponseDto(
            accessToken = access,
            refreshToken = refresh,
            expiredAt = expiredAt
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}