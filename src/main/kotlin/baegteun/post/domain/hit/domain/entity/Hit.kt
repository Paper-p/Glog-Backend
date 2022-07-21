package baegteun.post.domain.hit.domain.entity

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash
class Hit(
    @Id
    var feedId: Long,

    var hitCount: Int
) {
    fun increaseHitCount() {
        hitCount += 1
    }
}