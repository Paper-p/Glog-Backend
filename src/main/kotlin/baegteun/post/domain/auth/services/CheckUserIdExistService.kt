package baegteun.post.domain.auth.services

import baegteun.post.domain.user.dao.UserUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CheckUserIdExistService(
    private val userUtil: UserUtil
) {
    fun execute(userId: String): ResponseEntity<Void> {
        userUtil.checkExistsUserId(userId)
        return ResponseEntity.ok().build()
    }
}