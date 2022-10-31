package baegteun.post.domain.comment.domain.entity

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.global.entity.BaseTimeIdEntity
import javax.persistence.*

@Entity
class Comment(
    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    val feed: Feed,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val createdBy: User
): BaseTimeIdEntity()