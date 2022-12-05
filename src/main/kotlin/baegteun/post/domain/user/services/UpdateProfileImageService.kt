package baegteun.post.domain.user.services

import baegteun.post.domain.user.domain.repository.UserRepository
import baegteun.post.domain.user.presentation.dto.request.UpdateProfileImageRequestDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateProfileImageService(
    private val userUtil: UserUtil,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute(dto: UpdateProfileImageRequestDto): ResponseEntity<Void> {
        val user = userUtil.fetchCurrentUser()
        user.updateProfile(dto.imageUrl)
        userRepository.save(user)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}