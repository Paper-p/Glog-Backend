package baegteun.post.domain.auth.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class RefreshTokenNotFoundException: PaperException(ErrorCode.REFRESH_TOKEN_NOT_FOUND) {
    companion object {
        val EXCEPTION: PaperException = RefreshTokenNotFoundException()
    }
}