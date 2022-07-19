package baegteun.post.domain.auth.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class PasswordMismatchException: PaperException(ErrorCode.PASSWORD_MISMATCH) {
    companion object {
        val EXCEPTION: PaperException = PasswordMismatchException()
    }
}