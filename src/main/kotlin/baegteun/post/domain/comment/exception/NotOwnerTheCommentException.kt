package baegteun.post.domain.comment.exception

import baegteun.post.domain.feed.exception.NotOwnerTheFeedException
import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class NotOwnerTheCommentException: PaperException(ErrorCode.NOT_OWNER_THE_COMMENT) {
    companion object {
        val EXCEPTION = NotOwnerTheFeedException()
    }
}