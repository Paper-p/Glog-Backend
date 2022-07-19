package baegteun.post.global.error.exception

import baegteun.post.global.error.ErrorCode

class PaperException(
    val errorCode: ErrorCode
): RuntimeException()