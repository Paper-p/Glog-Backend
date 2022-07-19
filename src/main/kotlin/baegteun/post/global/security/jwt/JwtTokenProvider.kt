package baegteun.post.global.security.jwt

import baegteun.post.domain.auth.domain.repository.RefreshTokenRepository
import baegteun.post.global.security.auth.AuthDetailsService
import baegteun.post.global.security.exception.ExpiredTokenException
import baegteun.post.global.security.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) {
    companion object {
        private const val ACCESS_TYPE = "access"
        private const val REFRESH_TYPE = "refresh"
        private const val ACCESS_EXP: Long = 60 * 15
        private const val REFRESH_EXP: Long = 60 * 60 * 24 * 7
    }

    fun generateAccessToken(userId: String): String =
        generateToken(userId, ACCESS_TYPE, jwtProperties.accessSecret, ACCESS_EXP)

    fun generateRefreshToken(userId: String): String =
        generateToken(userId, REFRESH_TYPE, jwtProperties.refreshSecret, REFRESH_EXP)

    fun exactUserIdFromRefreshToken(token: String): String =
        getTokenSubject(token, jwtProperties.refreshSecret)

    fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if (token != null && token.startsWith("Bearer "))
            return token.replace("Bearer ", "")
        return null
    }

    fun getExpiredTime(): ZonedDateTime = ZonedDateTime.now().plusSeconds(ACCESS_EXP)

    fun getRefreshTimeToLive(): Long = REFRESH_EXP

    fun authentication(token: String): Authentication {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token, jwtProperties.accessSecret))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun generateToken(userId: String, type: String, secret: String, exp: Long): String =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secret)
            .setSubject(userId)
            .claim("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()

    private fun getTokenBody(token: String, secret: String): Claims {
        try {
            return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException.EXCEPTION
        } catch (e: Exception) {
            throw InvalidTokenException.EXCEPTION
        }
    }

    private fun getTokenSubject(token: String, secret: String): String =
        getTokenBody(token, secret).subject
}