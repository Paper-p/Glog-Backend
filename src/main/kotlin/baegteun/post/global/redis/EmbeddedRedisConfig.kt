package baegteun.post.global.redis

import baegteun.post.global.redis.properties.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Configuration
@Profile("local")
class EmbeddedRedisConfig(
    private val redisProperties: RedisProperties
) {
    companion object {
        private var redisServer: RedisServer? = null
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory =
        LettuceConnectionFactory("localhost", 6379)

    @PostConstruct
    fun redisServer() {
        if (redisServer == null) {
            redisServer = RedisServer(redisProperties.port)
            redisServer?.start()
        }
    }

    @PreDestroy
    fun cleanup() {
        if (redisServer != null && redisServer?.isActive == true) {
            redisServer?.stop()
        }
    }
}
