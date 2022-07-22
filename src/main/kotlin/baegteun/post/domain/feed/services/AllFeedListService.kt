package baegteun.post.domain.feed.services

import baegteun.post.domain.feed.domain.repository.FeedRepository
import baegteun.post.domain.feed.presentation.dto.response.AllFeedListResponseDto
import baegteun.post.domain.feed.presentation.dto.response.FeedListDto
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Transactional(readOnly = true)
@Service
class AllFeedListService(
    private val feedRepository: FeedRepository
) {
    fun execute(pageable: Pageable): ResponseEntity<AllFeedListResponseDto> {
        val lists = feedRepository.findAll(pageable).toList()
        val response = AllFeedListResponseDto(
            size = pageable.pageSize,
            page = pageable.pageNumber,
            list = lists.map { FeedListDto(it) }
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}