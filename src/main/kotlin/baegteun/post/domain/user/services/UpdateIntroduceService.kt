package baegteun.post.domain.user.services

import baegteun.post.domain.user.domain.repository.UserRepository
import baegteun.post.domain.user.presentation.dto.request.UpdateIntroduceRequestDto
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateIntroduceService(
    private val userRepository: UserRepository,
    private val userUtil: UserUtil
) {
    @Transactional
    fun execute(updateIntroduceRequestDto: UpdateIntroduceRequestDto): ResponseEntity<Void> {
        val currentUser = userUtil.fetchCurrentUser()
        currentUser.introduce = updateIntroduceRequestDto.introduce
        userRepository.save(currentUser)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}