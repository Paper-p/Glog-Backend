package baegteun.post.domain.auth.presentation.dto.request

import javax.validation.constraints.NotBlank

data class SigninRequestDto(
    @field:NotBlank(message = "userId는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val userId: String,

    @field:NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val password: String
)