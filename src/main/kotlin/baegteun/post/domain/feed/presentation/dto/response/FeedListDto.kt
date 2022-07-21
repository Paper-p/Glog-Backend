package baegteun.post.domain.feed.presentation.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class FeedListDto(
    val id: Long,
    val title: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: ZonedDateTime
)