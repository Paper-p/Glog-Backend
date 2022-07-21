package baegteun.post.domain.feed.presentation.dto.response

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.user.domain.entity.User
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZoneId
import java.time.ZonedDateTime

data class DetailFeedResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val imageUrls: List<String>,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: ZonedDateTime,
    val hit: Int,
    val author: AuthorDto,
    val isMine: Boolean
) {
    constructor(feed: Feed, imageUrls: List<String>, hitCount: Int, isMine: Boolean): this(
        feed.id,
        feed.title,
        feed.content,
        imageUrls,
        feed.createdAt.atZone(ZoneId.of("Asia/Seoul")),
        hitCount,
        AuthorDto(feed.user),
        isMine
    )
}