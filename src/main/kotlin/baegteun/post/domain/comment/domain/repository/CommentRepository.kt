package baegteun.post.domain.comment.domain.repository

import baegteun.post.domain.comment.domain.entity.Comment
import baegteun.post.domain.feed.domain.entity.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun countByFeed(feed: Feed): Int
    fun findAllByFeed(feed: Feed): List<Comment>
}