package baegteun.post.domain.comment.services

import baegteun.post.domain.comment.domain.repository.CommentRepository
import baegteun.post.domain.comment.exception.CommentNotFoundException
import baegteun.post.domain.comment.exception.NotOwnerTheCommentException
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DeleteCommentService(
    private val commentRepository: CommentRepository,
    private val userUtil: UserUtil
) {
    @Transactional
    fun execute(commentId: Long): ResponseEntity<Void> {
        val user = userUtil.fetchCurrentUser()
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw CommentNotFoundException.EXCEPTION
        if (!Objects.equals(user.id, comment.createdBy.id))
            throw NotOwnerTheCommentException.EXCEPTION
        commentRepository.delete(comment)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}