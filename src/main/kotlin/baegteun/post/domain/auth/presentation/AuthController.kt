package baegteun.post.domain.auth.presentation

import baegteun.post.domain.auth.presentation.dto.request.SigninRequestDto
import baegteun.post.domain.auth.presentation.dto.request.SignupRequestDto
import baegteun.post.domain.auth.presentation.dto.response.SigninResponseDto
import baegteun.post.domain.auth.services.CheckNicknameExistService
import baegteun.post.domain.auth.services.CheckUserIdExistService
import baegteun.post.domain.auth.services.SigninService
import baegteun.post.domain.auth.services.SignupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("auth")
class AuthController(
    private val signupService: SignupService,
    private val signinService: SigninService,
    private val checkUserIdExistService: CheckUserIdExistService,
    private val checkNicknameExistService: CheckNicknameExistService
) {
    @PostMapping("signup")
    fun signup(@RequestBody @Valid signupRequestDto: SignupRequestDto): ResponseEntity<Void> =
        signupService.execute(signupRequestDto)

    @PostMapping("signin")
    fun signin(@RequestBody @Valid signinRequestDto: SigninRequestDto): ResponseEntity<SigninResponseDto> =
        signinService.execute(signinRequestDto)

    @RequestMapping(name = "valid-id", method = [RequestMethod.HEAD])
    fun checkUseridExist(@RequestParam userId: String): ResponseEntity<Void> =
        checkUserIdExistService.execute(userId)

    @RequestMapping(name = "valid-name", method = [RequestMethod.HEAD])
    fun checkNicknameExist(@RequestParam nickname: String): ResponseEntity<Void> =
        checkNicknameExistService.execute(nickname)
}