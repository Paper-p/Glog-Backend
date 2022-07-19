package baegteun.post.global.error

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(val status: Int, val message: String) {

}