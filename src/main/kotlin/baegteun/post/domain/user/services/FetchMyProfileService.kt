package baegteun.post.domain.user.services

import baegteun.post.domain.user.presentation.dto.response.MyPageResponseDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FetchMyProfileService(
    private val userUtil: UserUtil
) {
    fun execute(): ResponseEntity<MyPageResponseDto> {
        val user = userUtil.fetchCurrentUser()
        val response = MyPageResponseDto(
            user,
            user.feeds
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}