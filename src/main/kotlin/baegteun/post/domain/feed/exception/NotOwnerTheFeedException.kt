package baegteun.post.domain.feed.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class NotOwnerTheFeedException: PaperException(ErrorCode.NOT_OWNER_THE_FEED) {
    companion object {
        val EXCEPTION = NotOwnerTheFeedException()
    }
}