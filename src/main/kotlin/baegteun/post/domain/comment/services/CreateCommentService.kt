package baegteun.post.domain.comment.services

import baegteun.post.domain.comment.domain.entity.Comment
import baegteun.post.domain.comment.domain.repository.CommentRepository
import baegteun.post.domain.comment.presentation.dto.request.CreateCommentRequestDto
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateCommentService(
    private val commentRepository: CommentRepository,
    private val feedUtil: FeedUtil,
    private val userUtil: UserUtil
) {
    @Transactional
    fun execute(feedId: Long, req: CreateCommentRequestDto): ResponseEntity<Void> {
        val user = userUtil.fetchCurrentUser()
        val feed = feedUtil.fetchFeedById(feedId)
        val comment = Comment(req.content, feed, user)
        commentRepository.save(comment)
        return ResponseEntity(HttpStatus.CREATED)
    }
}