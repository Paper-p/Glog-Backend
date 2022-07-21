package baegteun.post.domain.feed.domain.repository

import baegteun.post.domain.feed.domain.entity.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository: JpaRepository<Feed, Long> {
}