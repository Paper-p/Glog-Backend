package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.repository.FeedImageRepository
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.exception.FeedNotFoundException
import baegteun.post.domain.feed.presentation.dto.response.DetailFeedResponseDto
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.hit.domain.entity.Hit
import baegteun.post.domain.hit.domain.repository.HitRepository
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Objects

@Transactional
@Service
class DetailFeedService(
    private val hitRepository: HitRepository,
    private val userUtil: UserUtil,
    private val feedUtil: FeedUtil
) {
    fun execute(feedId: Long): ResponseEntity<DetailFeedResponseDto> {
        val feed = feedUtil.fetchFeedById(feedId)
        val feedImages = feedUtil.fetchFeedImages(feed)
        val hit = hitRepository.findById(feedId).orElseGet { Hit(feedId, 0) }
        hit.increaseHitCount()
        hitRepository.save(hit)
        val isMine = Objects.equals(feed.user.id, userUtil.fetchCurrentUser().id)
        val response = DetailFeedResponseDto(feed, feedImages.map { it.url }, hit.hitCount, isMine)
        return ResponseEntity(response, HttpStatus.OK)
    }
}