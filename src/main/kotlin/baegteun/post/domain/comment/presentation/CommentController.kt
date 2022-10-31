package baegteun.post.domain.comment.presentation

import baegteun.post.domain.comment.presentation.dto.request.CreateCommentRequestDto
import baegteun.post.domain.comment.presentation.dto.request.UpdateCommentRequestDto
import baegteun.post.domain.comment.services.CreateCommentService
import baegteun.post.domain.comment.services.DeleteCommentService
import baegteun.post.domain.comment.services.UpdateCommentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class CommentController(
    private val createCommentService: CreateCommentService,
    private val updateCommentService: UpdateCommentService,
    private val deleteCommentService: DeleteCommentService
) {
    @PostMapping("{feed-id}")
    fun createComment(
        @NotBlank @PathVariable("feed-id") feedId: Long,
        @Valid @RequestBody req: CreateCommentRequestDto
    ) = createCommentService.execute(feedId, req)

    @PatchMapping("{comment-id}")
    fun updateComment(
        @NotBlank @PathVariable("comment-id") commentId: Long,
        @Valid @RequestBody req: UpdateCommentRequestDto
    ) = updateCommentService.execute(commentId, req)

    @DeleteMapping("{comment-id}")
    fun deleteComment(
        @NotBlank @PathVariable("comment-id") commentId: Long
    ) = deleteCommentService.execute(commentId)
}