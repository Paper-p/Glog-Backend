package baegteun.post.domain.feed.presentation.dto.response

import baegteun.post.domain.comment.domain.entity.Comment
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZoneId
import java.time.ZonedDateTime

data class SingleCommentDto(
    val author: AuthorDto,
    val content: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: ZonedDateTime,
    var isMine: Boolean
) {
    constructor(comment: Comment, isMine: Boolean): this(
        AuthorDto(comment.createdBy),
        comment.content,
        comment.createdAt.atZone(ZoneId.of("Asia/Seoul")),
        isMine
    )
}