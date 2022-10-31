package baegteun.post.domain.comment.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class CommentNotFoundException: PaperException(ErrorCode.COMMENT_NOT_FOUND) {
    companion object {
        val EXCEPTION = CommentNotFoundException()
    }
}