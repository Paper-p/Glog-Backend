package baegteun.post.domain.feed.utils.impl

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.exception.FeedNotFoundException
import baegteun.post.domain.feed.presentation.dto.response.FeedListDto
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.heart.domain.entity.Heart
import baegteun.post.domain.heart.domain.repository.HeartRepository
import baegteun.post.domain.hit.domain.repository.HitRepository
import baegteun.post.domain.tag.domain.repository.TagRepository
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class FeedUtilImpl(
    private val feedRepository: FeedRepository,
    private val hitRepository: HitRepository,
    private val heartRepository: HeartRepository,
    private val tagRepository: TagRepository,
    private val userUtil: UserUtil
): FeedUtil {
    override fun fetchFeedById(id: Long): Feed =
        feedRepository.findById(id).orElseThrow { FeedNotFoundException.EXCEPTION }

    override fun fetchFeedListByUser(user: User): List<FeedListDto> =
        feedListToDto(user.feeds)

    override fun feedListToDto(feeds: List<Feed>): List<FeedListDto> =
        feeds.map {
            val hit = hitRepository.findById(it.id)
                .orElseThrow { FeedNotFoundException.EXCEPTION }
            val heartCount = heartRepository.countByFeed(it)
            val isHeart = if (SecurityContextHolder.getContext().authentication.principal !is UserDetails) {
                false
            } else {
                heartRepository.existsByUserAndFeed(userUtil.fetchCurrentUser(), it)
            }
            FeedListDto(
                it,
                hit.hitCount,
                heartCount,
                isHeart,
                tagRepository.findAllByFeed(it).map { tag -> tag.title }
            )
        }

    override fun feedLikeToggle(id: Long, liked: Boolean) {
        val user = userUtil.fetchCurrentUser()
        val feed = feedRepository.findById(id)
            .orElseThrow { FeedNotFoundException.EXCEPTION }
        if (liked) {
            if (!heartRepository.existsByUserAndFeed(user, feed))
                heartRepository.save(Heart(feed, user))
        } else {
            val heart = heartRepository.findByUserAndFeed(user, feed)
            if (heart != null) {
                heartRepository.delete(heart)
            }
        }

    }
}