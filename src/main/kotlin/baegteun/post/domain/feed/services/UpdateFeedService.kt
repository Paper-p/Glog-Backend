package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.entity.FeedImage
import baegteun.post.domain.feed.domain.repository.FeedImageRepository
import baegteun.post.domain.feed.exception.NotOwnerTheFeedException
import baegteun.post.domain.feed.presentation.dto.request.UpdateFeedRequestDto
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Objects

@Transactional
@Service
class UpdateFeedService(
    private val feedUtil: FeedUtil,
    private val userUtil: UserUtil,
    private val feedImageRepository: FeedImageRepository
) {
    fun execute(feedId: Long, req: UpdateFeedRequestDto): ResponseEntity<Void> {
        val feed = feedUtil.fetchFeedById(feedId)

        if (!Objects.equals(feed.user.id, userUtil.fetchCurrentUser().id))
            throw NotOwnerTheFeedException.EXCEPTION

        feed.update(req.title, req.content)

        val feedImages = feed.feedImages

        val removedImages = feedImages.filter { !req.imageUrls.contains(it.url) }
        feedImageRepository.deleteAll(removedImages)

        val addedImages = req.imageUrls.filter { new -> !feedImages.map { it.url }.contains(new) }
        feedImageRepository.saveAll(addedImages.map { FeedImage(it, feed) })

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}