package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.repository.FeedImageRepository
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.exception.NotOwnerTheFeedException
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.user.utils.UserUtil
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
    private val feedImageRepository: FeedImageRepository
) {
    fun execute(feedId: Long): ResponseEntity<Void> {
        val feed = feedUtil.fetchFeedById(feedId)

        if (!Objects.equals(feed.user.id, userUtil.fetchCurrentUser().id))
            throw NotOwnerTheFeedException.EXCEPTION
        feedUtil.fetchFeedImages(feed).forEach { feedImageRepository.deleteById(it.id) }
        feedRepository.delete(feed)

        return ResponseEntity.ok().build()
    }
}