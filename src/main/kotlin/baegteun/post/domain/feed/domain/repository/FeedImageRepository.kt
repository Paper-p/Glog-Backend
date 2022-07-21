package baegteun.post.domain.feed.domain.repository

import baegteun.post.domain.feed.domain.entity.FeedImage
import org.springframework.data.repository.CrudRepository

interface FeedImageRepository: CrudRepository<FeedImage, Long> {
}