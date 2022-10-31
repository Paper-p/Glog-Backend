package baegteun.post.domain.feed.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

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