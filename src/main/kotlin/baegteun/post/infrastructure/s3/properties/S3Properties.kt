package baegteun.post.infrastructure.s3.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.s3")
data class S3Properties(
    val bucket: String
)