package baegteun.post.global.security.filter

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.ErrorResponse
import baegteun.post.global.error.exception.PaperException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: PaperException) {
            sendError(response, e.errorCode)
        } catch (e: Exception) {
            logger.error(e)
            sendError(response, ErrorCode.INTERNAL_SERVER_ERROR)
        }
    }

    private fun sendError(response: HttpServletResponse, errorCode: ErrorCode) {
        val errorResponse = ErrorResponse(errorCode.status, errorCode.message)
        val responseString = objectMapper.writeValueAsString(errorResponse)

        response.status = errorCode.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(responseString)
    }
}