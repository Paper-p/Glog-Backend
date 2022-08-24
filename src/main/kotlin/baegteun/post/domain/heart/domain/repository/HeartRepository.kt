package baegteun.post.domain.heart.domain.repository

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.heart.domain.entity.Heart
import baegteun.post.domain.user.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface HeartRepository: CrudRepository<Heart, Long> {
    fun countByFeed(feed: Feed): Int
    fun existsByUser(user: User): Boolean
}