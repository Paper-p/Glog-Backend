package baegteun.post.domain.feed.domain.entity

import baegteun.post.domain.tag.domain.entity.Tag
import baegteun.post.domain.user.domain.entity.User
import baegteun.post.global.entity.BaseTimeIdEntity
import javax.persistence.*

@Entity
class Feed(
    @Column(nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,)
    val user: User,

    var thumbnail: String?,

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var tags: List<Tag> = listOf()
): BaseTimeIdEntity() {
    fun update(title: String, content: String, thumbnail: String?) {
        this.title = title
        this.content = content
        this.thumbnail = thumbnail
    }
}