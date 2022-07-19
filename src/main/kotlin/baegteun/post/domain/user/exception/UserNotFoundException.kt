package baegteun.post.domain.user.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class UserNotFoundException: PaperException(ErrorCode.USER_NOT_FOUND){
    companion object {
        val EXCEPTION: PaperException = UserNotFoundException()
    }
}