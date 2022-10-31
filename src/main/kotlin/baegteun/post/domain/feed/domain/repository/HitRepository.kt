package baegteun.post.domain.feed.domain.repository

import baegteun.post.domain.feed.domain.entity.Hit
import org.springframework.data.repository.CrudRepository

interface HitRepository: CrudRepository<Hit, Long> {

}