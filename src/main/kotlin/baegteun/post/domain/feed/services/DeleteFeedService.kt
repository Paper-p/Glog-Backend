package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.repository.FeedImageRepository
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.exception.NotOwnerTheFeedException
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.hit.domain.repository.HitRepository
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class DeleteFeedService(
    private val feedUtil: FeedUtil,
    private val userUtil: UserUtil,
    private val feedRepository: FeedRepository,
    private val hitRepository: HitRepository
) {
    fun execute(feedId: Long): ResponseEntity<Void> {
        val feed = feedUtil.fetchFeedById(feedId)

        if (!Objects.equals(feed.user.id, userUtil.fetchCurrentUser().id))
            throw NotOwnerTheFeedException.EXCEPTION
        feedRepository.delete(feed)
        hitRepository.deleteById(feed.id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}