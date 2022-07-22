package baegteun.post.domain.user.presentation

import baegteun.post.domain.user.presentation.dto.request.UpdateProfileImageRequestDto
import baegteun.post.domain.user.presentation.dto.response.MyPageResponseDto
import baegteun.post.domain.user.services.FetchMyProfileService
import baegteun.post.domain.user.services.UpdateProfileImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UserController(
    private val fetchMyProfileService: FetchMyProfileService,
    private val updateProfileImageService: UpdateProfileImageService
) {
    @GetMapping("my")
    fun fetchMyProfile(): ResponseEntity<MyPageResponseDto> =
        fetchMyProfileService.execute()

    @PutMapping
    fun updateProfileImage(@Valid @RequestBody updateProfileImageRequestDto: UpdateProfileImageRequestDto): ResponseEntity<Void> =
        updateProfileImageService.execute(updateProfileImageRequestDto)
}