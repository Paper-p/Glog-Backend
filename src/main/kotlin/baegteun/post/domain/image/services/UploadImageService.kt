package baegteun.post.domain.image.services

import baegteun.post.domain.image.presentation.dto.response.UploadImageResponseDto
import baegteun.post.infrastructure.s3.utils.ImageUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
@Service
class UploadImageService(
    private val imageUtil: ImageUtil
) {
    fun execute(images: List<MultipartFile>): ResponseEntity<UploadImageResponseDto> {
        val images = images.map(imageUtil::uploadImage)
        val response = UploadImageResponseDto(images)
        return ResponseEntity(response, HttpStatus.OK)
    }
}