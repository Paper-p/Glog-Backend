package baegteun.post.domain.user.services

import baegteun.post.domain.user.presentation.dto.response.MyProfileResponseDto
import baegteun.post.domain.user.presentation.dto.response.UserProfileResponseDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FetchUserProfileService(
    private val userUtil: UserUtil
) {
    fun execute(userId: String): ResponseEntity<UserProfileResponseDto> {
        val user = userUtil.fetchUserByUserId(userId)
        val response = MyProfileResponseDto(
            user,
            user.feeds
        )
        return ResponseEntity.ok().build()
    }
}