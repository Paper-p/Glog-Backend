package baegteun.post.domain.feed.presentation.dto.response

data class FeedListResponseDto(
    val size: Int,
    val page: Int,
    val list: List<FeedListDto>
)