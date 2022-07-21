package baegteun.post.domain.feed.presentation

import baegteun.post.domain.feed.presentation.dto.response.AllFeedListResponseDto
import baegteun.post.domain.feed.services.AllFeedListService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("feed")
class FeedController(
    private val allFeedListService: AllFeedListService
) {
    @GetMapping("list")
    fun allFeedList(@PageableDefault(size = 5) pageable: Pageable): ResponseEntity<AllFeedListResponseDto> =
        allFeedListService.execute(pageable)
}