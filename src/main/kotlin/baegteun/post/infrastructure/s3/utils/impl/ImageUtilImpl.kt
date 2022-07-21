package baegteun.post.infrastructure.s3.utils.impl

import baegteun.post.infrastructure.s3.exception.FailedToSaveImageException
import baegteun.post.infrastructure.s3.exception.InvalidImageException
import baegteun.post.infrastructure.s3.properties.S3Properties
import baegteun.post.infrastructure.s3.utils.ImageUtil
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.web.multipart.MultipartFile
import java.util.UUID


class ImageUtilImpl(
    private val s3Properties: S3Properties,
    private val amazonS3Client: AmazonS3Client
): ImageUtil {
    override fun uploadImage(image: MultipartFile): String {
        if (image.isEmpty) throw InvalidImageException.EXCEPTION

        val fileName = "${s3Properties.bucket}/${UUID.randomUUID()}${image.originalFilename}"

        try {
            val req = PutObjectRequest(
                s3Properties.bucket,
                fileName,
                image.inputStream,
                getImageMetadata(image)
            )
            amazonS3Client.putObject(req.withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (e: Exception) {
            throw FailedToSaveImageException.EXCEPTION
        }

        return getFileUrl(fileName)
    }

    private fun getImageMetadata(image: MultipartFile): ObjectMetadata? {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = image.size
        objectMetadata.contentType = image.contentType
        return objectMetadata
    }

    private fun getFileUrl(fileName: String): String {
        return amazonS3Client.getUrl(s3Properties.bucket, fileName).toString()
    }
}