package baegteun.post.domain.auth.presentation.dto.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class SignupRequestDto(
    @field:NotNull
    @field:Size(min = 4, max = 20, message = "nickname은 최소 4자, 최대 20자 입니다.")
    val nickname: String,

    @field:Size(max = 100, message = "한줄소개는 최대 100자 입니다.")
    val introduce: String? = null,

    @field:NotNull
    @field:Size(min = 4, max = 20, message = "userId는 최소 4자, 최대 20자 입니다.")
    val userId: String,

    @field:NotNull
    @field:Size(min = 8, max = 40, message = "password는 최소 8자, 최대 40자 입니다.")
    val password: String
)