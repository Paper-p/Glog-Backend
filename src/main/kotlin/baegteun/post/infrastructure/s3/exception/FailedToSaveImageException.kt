package baegteun.post.infrastructure.s3.exception

import baegteun.post.global.error.ErrorCode
import baegteun.post.global.error.exception.PaperException

class FailedToSaveImageException: PaperException(ErrorCode.FAILED_TO_SAVE_IMAGE) {
    companion object {
        val EXCEPTION = FailedToSaveImageException()
    }
}