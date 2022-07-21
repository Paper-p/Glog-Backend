package baegteun.post.domain.feed.domain.repository

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.entity.FeedImage
import org.springframework.data.repository.CrudRepository

interface FeedImageRepository: CrudRepository<FeedImage, Long> {
    fun findAllByFeed(feed: Feed): List<FeedImage>
}