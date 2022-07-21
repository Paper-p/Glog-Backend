package baegteun.post.domain.feed.presentation.dto.response

data class AllFeedListResponseDto(
    val size: Int,
    val page: Int,
    val list: List<FeedListDto>
)

