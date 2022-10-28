package baegteun.post.domain.image.presentation

import baegteun.post.domain.image.presentation.dto.response.UploadImageResponseDto
import baegteun.post.domain.image.services.UploadImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("image")
class ImageController(
    private val uploadImageService: UploadImageService
) {
    @PostMapping
    fun uploadImage(@Valid @NotNull image: MultipartFile): ResponseEntity<UploadImageResponseDto> =
        uploadImageService.execute(image)
}