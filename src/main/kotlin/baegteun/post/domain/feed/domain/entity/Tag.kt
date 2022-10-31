package baegteun.post.domain.feed.domain.entity

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.global.entity.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.NotBlank

@Entity
class Tag(
    @field:NotBlank
    val title: String,

    @ManyToOne
    @JoinColumn(name = "feed_id")
    var feed: Feed
): BaseIdEntity()