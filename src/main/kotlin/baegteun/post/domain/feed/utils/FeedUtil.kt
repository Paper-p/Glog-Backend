package baegteun.post.domain.feed.utils

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.presentation.dto.response.FeedListDto
import baegteun.post.domain.user.domain.entity.User

interface FeedUtil {
    fun fetchFeedById(id: Long): Feed
    fun fetchFeedListByUser(user: User): List<FeedListDto>
    fun feedListToDto(feed: List<Feed>): List<FeedListDto>
}