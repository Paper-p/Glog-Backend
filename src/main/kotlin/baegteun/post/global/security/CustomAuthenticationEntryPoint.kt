package baegteun.post.global.security

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val errorCode = ErrorCode.UNAUTHORIZED
        val responseString = objectMapper.writeValueAsString(ErrorResponse(errorCode.status, errorCode.message))

        response?.status = errorCode.status
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.writer?.write(responseString)
    }
}