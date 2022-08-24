package baegteun.post.domain.auth.presentation

import baegteun.post.domain.auth.presentation.dto.request.SigninRequestDto
import baegteun.post.domain.auth.presentation.dto.request.SignupRequestDto
import baegteun.post.domain.auth.presentation.dto.response.SigninResponseDto
import baegteun.post.domain.auth.presentation.dto.response.TokenRefreshResponseDto
import baegteun.post.domain.auth.services.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("auth")
class AuthController(
    private val signupService: SignupService,
    private val signinService: SigninService,
    private val checkUserIdExistService: CheckUserIdExistService,
    private val checkNicknameExistService: CheckNicknameExistService,
    private val tokenRefreshService: TokenRefreshService
) {
    @PostMapping("signup")
    fun signup(@RequestBody @Valid signupRequestDto: SignupRequestDto): ResponseEntity<Void> =
        signupService.execute(signupRequestDto)

    @PostMapping("signin")
    fun signin(@RequestBody @Valid signinRequestDto: SigninRequestDto): ResponseEntity<SigninResponseDto> =
        signinService.execute(signinRequestDto)

    @RequestMapping("/valid-id", method = [RequestMethod.HEAD])
    fun checkUserIdExist(@NotBlank @RequestParam userId: String): ResponseEntity<Void> =
        checkUserIdExistService.execute(userId)

    @RequestMapping("/valid-name", method = [RequestMethod.HEAD])
    fun checkNicknameExist(@NotBlank @RequestParam nickname: String): ResponseEntity<Void> =
        checkNicknameExistService.execute(nickname)

    @PatchMapping
    fun tokenRefresh(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenRefreshResponseDto> =
        tokenRefreshService.execute(refreshToken)
}