package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.repository.HitRepository
import baegteun.post.domain.feed.presentation.dto.response.HotFeedListResponseDto
import baegteun.post.domain.feed.utils.FeedUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class HotFeedListService(
    private val hitRepository: HitRepository,
    private val feedUtil: FeedUtil
) {
    fun execute(): ResponseEntity<HotFeedListResponseDto> {
        val list = hitRepository.findTop4ByOrderByHitCountDesc()
            .map { feedUtil.fetchFeedById(it.feedId) }
        val res = feedUtil.feedListToDto(list)
        return ResponseEntity(HotFeedListResponseDto(res), HttpStatus.OK)
    }
}