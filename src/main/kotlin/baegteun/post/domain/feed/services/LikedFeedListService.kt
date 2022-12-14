package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.repository.HeartRepository
import baegteun.post.domain.feed.presentation.dto.response.LikedFeedListResponseDto
import baegteun.post.domain.feed.utils.FeedUtil
import baegteun.post.domain.user.utils.UserUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikedFeedListService(
    private val heartRepository: HeartRepository,
    private val userUtil: UserUtil,
    private val feedUtil: FeedUtil
) {
    @Transactional(readOnly = true)
    fun execute(): ResponseEntity<LikedFeedListResponseDto> = userUtil.fetchCurrentUser()
        .let { heartRepository.findAllByUser(it) }
        .map { it.feed }
        .let { feedUtil.feedListToDto(it) }
        .let { ResponseEntity(LikedFeedListResponseDto(it), HttpStatus.OK) }
}
