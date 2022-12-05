package baegteun.post.domain.image.services

import baegteun.post.domain.image.presentation.dto.response.UploadImageResponseDto
import baegteun.post.infrastructure.s3.utils.ImageUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class UploadImageService(
    private val imageUtil: ImageUtil
) {
    @Transactional
    fun execute(image: MultipartFile): ResponseEntity<UploadImageResponseDto> {
        val imageUrl = imageUtil.uploadImage(image = image)
        val response = UploadImageResponseDto(imageUrl)
        return ResponseEntity(response, HttpStatus.OK)
    }
}
