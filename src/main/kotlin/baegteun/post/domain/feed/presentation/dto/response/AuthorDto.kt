package baegteun.post.domain.feed.presentation.dto.response

import baegteun.post.domain.user.domain.entity.User

data class AuthorDto(
    val userId: String,
    val nickname: String,
    val profileImageUrl: String
) {
    constructor(user: User): this(user.userId, user.nickname, user.profileImageUrl)
}