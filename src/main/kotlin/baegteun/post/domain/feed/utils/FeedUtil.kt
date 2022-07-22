package baegteun.post.domain.feed.utils

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.entity.FeedImage

interface FeedUtil {
    fun fetchFeedById(id: Long): Feed
}