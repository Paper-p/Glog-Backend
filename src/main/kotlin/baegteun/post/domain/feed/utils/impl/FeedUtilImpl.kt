package baegteun.post.domain.feed.utils.impl

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.entity.FeedImage
import baegteun.post.domain.feed.domain.repository.FeedImageRepository
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.exception.FeedNotFoundException
import baegteun.post.domain.feed.utils.FeedUtil
import org.springframework.stereotype.Component

@Component
class FeedUtilImpl(
    private val feedRepository: FeedRepository,
    private val feedImageRepository: FeedImageRepository
): FeedUtil {
    override fun fetchFeedById(id: Long): Feed =
        feedRepository.findById(id).orElseThrow { FeedNotFoundException.EXCEPTION }

    override fun fetchFeedImages(feed: Feed): List<FeedImage> =
        feedImageRepository.findAllByFeed(feed)

}