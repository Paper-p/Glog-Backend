package baegteun.post.global.security.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class ExpiredTokenException: PaperException(ErrorCode.EXPIRED_TOKEN) {
    companion object {
        val EXCEPTION: PaperException = ExpiredTokenException()
    }
}