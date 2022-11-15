package baegteun.post.domain.user.presentation.dto.response

data class MyMiniProfileResponseDto(
    val userId: Long,
    val nickname: String,
    val profileImageUrl: String
)