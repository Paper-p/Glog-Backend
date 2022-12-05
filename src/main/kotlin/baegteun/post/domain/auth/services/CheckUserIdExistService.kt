package baegteun.post.domain.auth.services

import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CheckUserIdExistService(
    private val userUtil: UserUtil
) {
    @Transactional(readOnly = true)
    fun execute(userId: String): ResponseEntity<Void> {
        userUtil.checkExistsUserId(userId)
        return ResponseEntity(HttpStatus.OK)
    }
}