package baegteun.post.domain.hit.domain.repository

import baegteun.post.domain.hit.domain.entity.Hit
import org.springframework.data.repository.CrudRepository

interface HitRepository: CrudRepository<Hit, Long> {

}