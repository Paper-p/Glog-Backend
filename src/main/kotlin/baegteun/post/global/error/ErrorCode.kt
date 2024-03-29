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

    NOT_OWNER_THE_FEED(403, "Not owner the feed"),
    NOT_OWNER_THE_COMMENT(403, "Not owner the comment"),

    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken Not Found"),
    FEED_NOT_FOUND(404, "Feed Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),

    AlreadyExistUserId(409, "UserId is already exist"),
    AlreadyExistNickname(409, "Nickname is already exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
}