package baegteun.post.domain.feed.presentation.dto.response

import baegteun.post.domain.feed.domain.entity.Feed
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.min

data class FeedListDto(
    val id: Long,
    val title: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: ZonedDateTime,
    val thumbnail: String?,
    val previewContent: String,
    val hit: Int,
    val likeCount: Int,
    val isLiked: Boolean,
    val tagList: List<String>
) {
    constructor(feed: Feed, hit: Int, likeCount: Int, isLiked: Boolean, tagList: List<String>): this(
        feed.id,
        feed.title,
        feed.createdAt.atZone(ZoneId.of("Asia/Seoul")),
        feed.feedImages.firstOrNull()?.url,
        feed.content.substring(0..min(64, feed.content.length)),
        hit,
        likeCount,
        isLiked,
        tagList
    )
}