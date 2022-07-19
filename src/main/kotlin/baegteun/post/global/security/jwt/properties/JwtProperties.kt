package baegteun.post.global.security.jwt.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.Base64

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    accessSecret: String,
    refreshSecret: String
) {
    val accessSecret: String = Base64.getEncoder().encodeToString(accessSecret.toByteArray());
    val refreshSecret: String = refreshSecret
}
