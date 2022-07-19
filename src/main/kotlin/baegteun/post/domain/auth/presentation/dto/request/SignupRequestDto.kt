package baegteun.post.domain.auth.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignupRequestDto(
    @field:NotBlank(message = "nickname은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(min = 4, max = 20, message = "nickname은 최소 4자, 최대 20자 입니다.")
    val nickname: String,

    @field:NotBlank(message = "userId는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(min = 4, max = 20, message = "userId는 최소 4자, 최대 20자 입니다.")
    val userId: String,

    @field:NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(min = 8, max = 40, message = "password는 최소 8자, 최대 40자 이빈다.")
    val password: String
)