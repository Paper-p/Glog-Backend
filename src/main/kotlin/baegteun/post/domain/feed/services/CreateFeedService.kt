package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.entity.FeedImage
import baegteun.post.domain.feed.domain.repository.FeedImageRepository
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.presentation.dto.request.CreateFeedRequestDto
import baegteun.post.domain.heart.domain.entity.Heart
import baegteun.post.domain.heart.domain.repository.HeartRepository
import baegteun.post.domain.hit.domain.entity.Hit
import baegteun.post.domain.hit.domain.repository.HitRepository
import baegteun.post.domain.tag.domain.entity.Tag
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreateFeedService(
    private val feedRepository: FeedRepository,
    private val feedImageRepository: FeedImageRepository,
    private val hitRepository: HitRepository,
    private val userUtil: UserUtil
) {
    fun execute(req: CreateFeedRequestDto): ResponseEntity<Void> {
        val user = userUtil.fetchCurrentUser()
        val feed = Feed(req.title, req.content, user)
        feed.tags = req.tags.distinct().map{ Tag(it, feed) }
        feedRepository.save(feed)
        feedImageRepository.saveAll(req.imageUrls.map { FeedImage(it, feed) })
        hitRepository.save(Hit(feed.id, 0))
        return ResponseEntity(HttpStatus.CREATED)
    }
}