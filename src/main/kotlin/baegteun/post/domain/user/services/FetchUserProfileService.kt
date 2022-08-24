package baegteun.post.domain.user.services

import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.user.presentation.dto.response.UserProfileResponseDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FetchUserProfileService(
    private val userUtil: UserUtil,
    private val feedUtil: FeedUtil
) {
    fun execute(userId: String): ResponseEntity<UserProfileResponseDto> {
        val user = userUtil.fetchUserByUserId(userId)
        val response = UserProfileResponseDto(
            user,
            feedUtil.fetchFeedListByUser(user)
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}