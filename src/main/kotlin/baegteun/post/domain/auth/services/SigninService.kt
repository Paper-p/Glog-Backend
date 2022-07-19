package baegteun.post.domain.auth.services

import baegteun.post.domain.auth.domain.entity.RefreshToken
import baegteun.post.domain.auth.domain.repository.RefreshTokenRepository
import baegteun.post.domain.auth.exception.PasswordMismatchException
import baegteun.post.domain.auth.presentation.dto.request.SigninRequestDto
import baegteun.post.domain.auth.presentation.dto.response.SigninResponseDto
import baegteun.post.domain.user.utils.UserUtil
import baegteun.post.global.security.jwt.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class SigninService(
    private val userUtil: UserUtil,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun execute(signinRequestDto: SigninRequestDto): ResponseEntity<SigninResponseDto> {
        val user = userUtil.fetchUserByUserId(signinRequestDto.userId)

        if (!passwordEncoder.matches(signinRequestDto.password, user.password))
            throw PasswordMismatchException.EXCEPTION

        val access = jwtTokenProvider.generateAccessToken(signinRequestDto.userId)
        val refresh = jwtTokenProvider.generateRefreshToken(signinRequestDto.userId)
        val expiredAt = jwtTokenProvider.getExpiredTime()

        val refreshToken = RefreshToken(
            userId = user.userId,
            token = passwordEncoder.encode(refresh),
            timeToLive = jwtTokenProvider.getRefreshTimeToLive()
        )
        refreshTokenRepository.save(refreshToken)

        val response = SigninResponseDto(
            accessToken = access,
            refreshToken = refresh,
            expiredAt = expiredAt
        )

        return ResponseEntity(response, HttpStatus.OK)
    }
}