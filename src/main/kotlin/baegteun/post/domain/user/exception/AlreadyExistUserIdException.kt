package baegteun.post.domain.user.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class AlreadyExistUserIdException: PaperException(ErrorCode.AlreadyExistUserId) {
    companion object {
        val EXCEPTION: PaperException = AlreadyExistUserIdException()
    }
}