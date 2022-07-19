package baegteun.post.global.error

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(val status: Int, val message: String) {
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST.value(), "Password Mismatch"),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Expired Token"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Invalid Token"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "User Not Found"),

    AlreadyExistUserId(HttpStatus.CONFLICT.value(), "UserId is already exist"),
    AlreadyExistNickname(HttpStatus.CONFLICT.value(), "Nickname is already exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
}