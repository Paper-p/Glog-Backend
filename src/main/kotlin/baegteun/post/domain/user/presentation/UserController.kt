package baegteun.post.domain.user.presentation

import baegteun.post.domain.user.presentation.dto.request.UpdateIntroduceRequestDto
import baegteun.post.domain.user.presentation.dto.request.UpdateNicknameRequestDto
import baegteun.post.domain.user.presentation.dto.request.UpdateProfileImageRequestDto
import baegteun.post.domain.user.presentation.dto.response.MyMiniProfileResponseDto
import baegteun.post.domain.user.presentation.dto.response.UserProfileResponseDto
import baegteun.post.domain.user.services.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("user")
class UserController(
    private val updateProfileImageService: UpdateProfileImageService,
    private val fetchUserProfileService: FetchUserProfileService,
    private val fetchMyMiniProfileService: FetchMyMiniProfileService,
    private val updateNicknameService: UpdateNicknameService,
    private val updateIntroduceService: UpdateIntroduceService
) {

    @GetMapping("profile")
    fun fetchMyMiniProfile(): ResponseEntity<MyMiniProfileResponseDto> =
        fetchMyMiniProfileService.execute()

    @PatchMapping("profile-image")
    fun updateProfileImage(@Valid @RequestBody updateProfileImageRequestDto: UpdateProfileImageRequestDto): ResponseEntity<Void> =
        updateProfileImageService.execute(updateProfileImageRequestDto)

    @PatchMapping("nickname")
    fun updateNickname(@Valid @RequestBody updateNicknameRequestDto: UpdateNicknameRequestDto): ResponseEntity<Void> =
        updateNicknameService.execute(updateNicknameRequestDto)

    @PatchMapping("introduce")
    fun updateIntroduce(@Valid @RequestBody updateIntroduceRequestDto: UpdateIntroduceRequestDto): ResponseEntity<Void> =
        updateIntroduceService.execute(updateIntroduceRequestDto)

    @GetMapping("{nickname}")
    fun fetchUserProfile(@NotBlank @PathVariable("nickname") nickname: String): ResponseEntity<UserProfileResponseDto> =
        fetchUserProfileService.execute(nickname)
}