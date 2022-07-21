package baegteun.post.infrastructure.s3.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class InvalidImageException: PaperException(ErrorCode.INVALID_IMAGE) {
    companion object {
        val EXCEPTION: PaperException = InvalidImageException()
    }
}