package baegteun.post.domain.feed.domain.entity

import baegteun.post.global.entity.BaseIdEntity
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class FeedImage(
    @field:NotNull
    @field:Size(max = 255)
    val url: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "feed_id", nullable = false)
    val feed: Feed
): BaseIdEntity()