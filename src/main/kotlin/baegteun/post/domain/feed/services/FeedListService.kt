package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.exception.FeedNotFoundException
import baegteun.post.domain.feed.presentation.dto.response.FeedListDto
import baegteun.post.domain.feed.presentation.dto.response.FeedListResponseDto
import baegteun.post.domain.heart.domain.repository.HeartRepository
import baegteun.post.domain.hit.domain.repository.HitRepository
import baegteun.post.domain.tag.domain.repository.TagRepository
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FeedListService(
    private val feedRepository: FeedRepository,
    private val hitRepository: HitRepository,
    private val heartRepository: HeartRepository,
    private val tagRepository: TagRepository,
    private val userUtil: UserUtil
) {
    fun execute(pageable: Pageable, keyword: String?): ResponseEntity<FeedListResponseDto> {
        val list: List<Feed> = if (keyword == null) {
            feedRepository.findAll(pageable).toList()
        } else {
            feedRepository.findAllByTitleContaining(pageable, keyword).toList()
        }

        val res = list.map {
            val hit = hitRepository.findById(it.id)
                .orElseThrow { FeedNotFoundException.EXCEPTION }
            val heartCount = heartRepository.countByFeed(it)
            val isHeart = if (SecurityContextHolder.getContext().authentication.name == null) {
                false
            } else {
                heartRepository.existsByUser(userUtil.fetchCurrentUser())
            }
            FeedListDto(
                it,
                hit.hitCount,
                heartCount,
                isHeart,
                tagRepository.findAllByFeed(it).map { tag -> tag.title }
            )
        }

        return ResponseEntity(
            FeedListResponseDto(
                pageable.pageSize,
                pageable.pageNumber,
                res
            ),
            HttpStatus.OK
        )
    }
}