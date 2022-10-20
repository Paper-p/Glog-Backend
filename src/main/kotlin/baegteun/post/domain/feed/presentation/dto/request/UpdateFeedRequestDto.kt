package baegteun.post.domain.feed.presentation.dto.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateFeedRequestDto(
    @field:NotNull
    @field:Size(min = 1, max = 50)
    val title: String,

    @field:NotNull
    @field:Size(min = 1)
    val content: String,

    @field:Size(max = 5)
    val imageUrls: List<String>,

    val tags: List<String>
)