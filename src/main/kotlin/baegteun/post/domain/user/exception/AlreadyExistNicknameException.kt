package baegteun.post.domain.user.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class AlreadyExistNicknameException: PaperException(ErrorCode.AlreadyExistNickname) {
    companion object {
        val EXCEPTION: PaperException = AlreadyExistNicknameException()
    }
}