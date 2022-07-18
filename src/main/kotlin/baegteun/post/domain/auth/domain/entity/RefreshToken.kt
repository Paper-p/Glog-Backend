package baegteun.post.domain.auth.domain.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@RedisHash
class RefreshToken(
    @Id
    var userId: Long,

    @Indexed
    var token: String,

    @TimeToLive
    var timeToLive: Long
)