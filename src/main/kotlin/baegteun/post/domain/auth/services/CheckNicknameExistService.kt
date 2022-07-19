package baegteun.post.domain.auth.services

import baegteun.post.domain.user.dao.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CheckNicknameExistService(
    private val userUtil: UserUtil
) {
    fun execute(nickname: String): ResponseEntity<Void> {
        userUtil.checkExistsNickname(nickname)
        return ResponseEntity(HttpStatus.OK)
    }
}