package baegteun.post.domain.feed.domain.entity

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
class Heart(
    @ManyToOne(fetch = FetchType.LAZY)
    var feed: Feed,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User
): BaseIdEntity()