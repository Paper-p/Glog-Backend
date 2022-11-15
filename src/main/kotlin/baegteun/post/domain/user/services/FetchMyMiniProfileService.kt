package baegteun.post.domain.user.services

import baegteun.post.domain.user.presentation.dto.response.MyMiniProfileResponseDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FetchMyMiniProfileService(
    private val userUtil: UserUtil
) {
    fun execute(): ResponseEntity<MyMiniProfileResponseDto> {
        val user = userUtil.fetchCurrentUser()
        val response = MyMiniProfileResponseDto(
            userId = user.id,
            nickname = user.nickname,
            profileImageUrl = user.profileImageUrl
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}