package baegteun.post.infrastructure.s3.utils

import org.springframework.web.multipart.MultipartFile

interface ImageUtil {
    fun uploadImage(image: MultipartFile): String
}