package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.utils.FeedUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LikeFeedService(
    private val feedUtil: FeedUtil
) {
    fun execute(id: Long): ResponseEntity<Void> {
        feedUtil.feedLikeToggle(id, true)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}