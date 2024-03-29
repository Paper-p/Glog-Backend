package baegteun.post.domain.feed.services

import baegteun.post.domain.comment.domain.repository.CommentRepository
import baegteun.post.domain.feed.presentation.dto.response.DetailFeedResponseDto
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.feed.domain.repository.HeartRepository
import baegteun.post.domain.feed.domain.entity.Hit
import baegteun.post.domain.feed.domain.repository.HitRepository
import baegteun.post.domain.feed.domain.repository.TagRepository
import baegteun.post.domain.feed.presentation.dto.response.SingleCommentDto
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Objects

@Service
class DetailFeedService(
    private val hitRepository: HitRepository,
    private val heartRepository: HeartRepository,
    private val tagRepository: TagRepository,
    private val commentRepository: CommentRepository,
    private val userUtil: UserUtil,
    private val feedUtil: FeedUtil
) {
    @Transactional
    fun execute(feedId: Long): ResponseEntity<DetailFeedResponseDto> {
        val feed = feedUtil.fetchFeedById(feedId)
        val hit = hitRepository.findById(feedId).orElseGet { Hit(feedId, 0) }
        hit.increaseHitCount()
        hitRepository.save(hit)

        val user: User? = try {
            userUtil.fetchCurrentUser()
        } catch (e: Exception) {
            null
        }
        val likeCount = heartRepository.countByFeed(feed)
        val isLiked = if(user != null) heartRepository.existsByUserAndFeed(user, feed) else false
        val tagList = tagRepository.findAllByFeed(feed).map { it.title }
        val comments = commentRepository.findAllByFeedOrderByCreatedAtDesc(feed).map { SingleCommentDto(it,if(user != null) Objects.equals(it.createdBy.id, user.id) else false ) }
        val isMine = if(user != null) Objects.equals(feed.user.id, user.id) else false

        val response = DetailFeedResponseDto(
            feed,
            feed.thumbnail,
            hit.hitCount,
            likeCount,
            isLiked,
            tagList,
            comments,
            isMine
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}