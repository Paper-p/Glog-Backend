package baegteun.post.domain.feed.presentation

import baegteun.post.domain.feed.presentation.dto.request.CreateFeedRequestDto
import baegteun.post.domain.feed.presentation.dto.request.UpdateFeedRequestDto
import baegteun.post.domain.feed.presentation.dto.response.DetailFeedResponseDto
import baegteun.post.domain.feed.presentation.dto.response.FeedListResponseDto
import baegteun.post.domain.feed.services.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("feed")
class FeedController(
    private val feedListService: FeedListService,
    private val detailFeedService: DetailFeedService,
    private val createFeedService: CreateFeedService,
    private val updateFeedService: UpdateFeedService,
    private val deleteFeedService: DeleteFeedService
) {
    @GetMapping("list")
    fun feedList(
        @PageableDefault(size = 5, page = 0) pageable: Pageable,
        @RequestParam("keyword") keyword: String?
    ): ResponseEntity<FeedListResponseDto> =
        feedListService.execute(pageable, keyword)

    @GetMapping("{id}")
    fun detailFeed(@NotBlank @PathVariable("id") id: Long): ResponseEntity<DetailFeedResponseDto> =
        detailFeedService.execute(id)

    @PostMapping
    fun createFeed(@Valid @RequestBody createFeedRequestDto: CreateFeedRequestDto): ResponseEntity<Void> =
        createFeedService.execute(createFeedRequestDto)

    @PatchMapping("{id}")
    fun updateFeed(@NotBlank @PathVariable("id") id: Long, @Valid @RequestBody updateFeedRequestDto: UpdateFeedRequestDto): ResponseEntity<Void> =
        updateFeedService.execute(id, updateFeedRequestDto)

    @DeleteMapping("{id}")
    fun deleteFeed(@NotBlank @PathVariable("id") id: Long): ResponseEntity<Void> =
        deleteFeedService.execute(id)
}