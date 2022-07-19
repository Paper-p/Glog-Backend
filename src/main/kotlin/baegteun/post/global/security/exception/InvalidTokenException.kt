package baegteun.post.global.security.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class InvalidTokenException: PaperException(ErrorCode.INVALID_TOKEN) {
    companion object {
        val EXCEPTION: PaperException = InvalidTokenException()
    }
}