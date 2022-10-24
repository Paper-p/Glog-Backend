package baegteun.post.domain.feed.presentation.dto.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateFeedRequestDto(
    @field:NotNull
    @field:Size(min = 1, max = 50)
    val title: String,

    @field:NotNull
    @field:Size(min = 1)
    val content: String,

    val thumbnail: String?,

    val tags: List<String>
)