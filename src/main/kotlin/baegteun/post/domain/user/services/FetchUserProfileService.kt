package baegteun.post.domain.user.services

import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.user.presentation.dto.response.UserProfileResponseDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Objects

@Transactional(readOnly = true)
@Service
class FetchUserProfileService(
    private val userUtil: UserUtil,
    private val feedUtil: FeedUtil
) {
    fun execute(userId: String): ResponseEntity<UserProfileResponseDto> {
        val user = userUtil.fetchUserByUserId(userId)
        val isMine: Boolean = try {
            val currentUser = userUtil.fetchCurrentUser()
            Objects.equals(currentUser.id, user.id)
        } catch (e: Exception) {
            false
        }
        val response = UserProfileResponseDto(
            user,
            feedUtil.fetchFeedListByUser(user),
            isMine
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}