package baegteun.post.domain.feed.presentation

import baegteun.post.domain.feed.presentation.dto.response.AllFeedListResponseDto
import baegteun.post.domain.feed.presentation.dto.response.DetailFeedResponseDto
import baegteun.post.domain.feed.services.AllFeedListService
import baegteun.post.domain.feed.services.DetailFeedService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("feed")
class FeedController(
    private val allFeedListService: AllFeedListService,
    private val detailFeedService: DetailFeedService
) {
    @GetMapping("list")
    fun allFeedList(@PageableDefault(size = 5) pageable: Pageable): ResponseEntity<AllFeedListResponseDto> =
        allFeedListService.execute(pageable)

    @GetMapping("\${id}")
    fun detailFeed(@NotBlank @PathVariable id: Long): ResponseEntity<DetailFeedResponseDto> =
        detailFeedService.execute(id)
}