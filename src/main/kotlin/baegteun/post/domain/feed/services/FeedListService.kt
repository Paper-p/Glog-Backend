package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.presentation.dto.response.FeedListResponseDto
import baegteun.post.domain.feed.utils.FeedUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FeedListService(
    private val feedRepository: FeedRepository,
    private val feedUtil: FeedUtil
) {
    fun execute(pageable: Pageable, keyword: String?): ResponseEntity<FeedListResponseDto> {
        val paging: PageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(Sort.Order.asc("createdAt")))
        val list: List<Feed> = if (keyword == null) {
            feedRepository.findAll(paging).toList()
        } else {
            feedRepository.findAllByTitleContaining(paging, keyword).toList()
        }

        val res = feedUtil.feedListToDto(list)

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