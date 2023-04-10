package baegteun.post.domain.auth.services

import baegteun.post.domain.auth.presentation.dto.request.SignupRequestDto
import baegteun.post.domain.user.utils.UserUtil
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.domain.user.domain.repository.UserRepository
import baegteun.post.infrastructure.image.DefaultImage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignupService(
    private val userUtil: UserUtil,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute(signupRequestDto: SignupRequestDto): ResponseEntity<Void> {
        userUtil.checkExistsUserId(signupRequestDto.userId)
        userUtil.checkExistsNickname(signupRequestDto.nickname)

        val user = User(
            userId = signupRequestDto.userId,
            nickname = signupRequestDto.nickname,
            password = passwordEncoder.encode(signupRequestDto.password),
            introduce = signupRequestDto.introduce,
            profileImageUrl = DefaultImage.PROFILE_IMAGE
        )
        userRepository.save(user)
        return ResponseEntity(HttpStatus.CREATED)
    }
}