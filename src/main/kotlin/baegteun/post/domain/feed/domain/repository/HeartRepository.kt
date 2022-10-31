package baegteun.post.domain.feed.domain.repository

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.entity.Heart
import baegteun.post.domain.user.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface HeartRepository: CrudRepository<Heart, Long> {
    fun countByFeed(feed: Feed): Int
    fun existsByUserAndFeed(user: User, feed: Feed): Boolean
    fun findByUserAndFeed(user: User, feed: Feed): Heart?
}