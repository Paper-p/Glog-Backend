package baegteun.post.domain.user.presentation.dto.response

import baegteun.post.domain.feed.presentation.dto.response.FeedListDto
import baegteun.post.domain.user.domain.entity.User

data class MyProfileResponseDto(
    val userId: String,
    val nickname: String,
    val profileImageUrl: String,
    val feedList: List<FeedListDto>
) {
    constructor(user: User, feeds: List<FeedListDto>): this(
        user.userId,
        user.nickname,
        user.profileImageUrl,
        feeds
    )
}