package baegteun.post.domain.user.presentation

import baegteun.post.domain.user.presentation.dto.response.MyPageResponseDto
import baegteun.post.domain.user.services.FetchMyProfileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val fetchMyProfileService: FetchMyProfileService
) {
    @GetMapping("my")
    fun fetchMyProfile(): ResponseEntity<MyPageResponseDto> =
        fetchMyProfileService.execute()
}