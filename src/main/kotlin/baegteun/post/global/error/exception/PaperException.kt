package baegteun.post.global.error.exception

import baegteun.post.global.error.ErrorCode

open class PaperException(
    val errorCode: ErrorCode
): RuntimeException()