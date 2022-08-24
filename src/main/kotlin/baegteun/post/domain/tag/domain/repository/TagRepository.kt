package baegteun.post.domain.tag.domain.repository

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.tag.domain.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {
    fun findAllByFeed(feed: Feed): List<Tag>
}