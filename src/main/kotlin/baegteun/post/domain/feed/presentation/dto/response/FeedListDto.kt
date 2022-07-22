package baegteun.post.domain.feed.presentation.dto.response

import baegteun.post.domain.feed.domain.entity.Feed
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZoneId
import java.time.ZonedDateTime

data class FeedListDto(
    val id: Long,
    val title: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: ZonedDateTime
) {
    constructor(feed: Feed): this(
        feed.id,
        feed.title,
        feed.createdAt.atZone(ZoneId.of("Asia/Seoul"))
    )
}