package baegteun.post.global.error

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(val status: Int, val message: String) {


    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "User Not Found")
}