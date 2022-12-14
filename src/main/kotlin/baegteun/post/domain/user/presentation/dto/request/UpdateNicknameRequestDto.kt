package baegteun.post.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UpdateNicknameRequestDto(
    @field:NotBlank
    @field:Size(min = 4, max = 20, message = "nickname은 최소 4자, 최대 20자 입니다.")
    val nickname: String
)