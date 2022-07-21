package baegteun.post.global.error

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(val status: Int, val message: String) {
    PASSWORD_MISMATCH(400, "Password Mismatch"),
    INVALID_IMAGE(400, "Invalid Image"),
    FAILED_TO_SAVE_IMAGE(400, "Failed to save image"),

    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    UNAUTHORIZED(401, "Unauthorized"),

    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken Not Found"),

    AlreadyExistUserId(409, "UserId is already exist"),
    AlreadyExistNickname(409, "Nickname is already exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
}