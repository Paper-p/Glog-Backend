package baegteun.post.domain.feed.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class FeedNotFoundException: PaperException(ErrorCode.FEED_NOT_FOUND) {
    companion object {
        val EXCEPTION = FeedNotFoundException()
    }
}