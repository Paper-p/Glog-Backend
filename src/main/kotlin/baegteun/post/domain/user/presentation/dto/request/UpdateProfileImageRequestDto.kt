package baegteun.post.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank

data class UpdateProfileImageRequestDto(
    @field:NotBlank
    val imageUrl: String
)