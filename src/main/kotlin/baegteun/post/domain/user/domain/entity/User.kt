package baegteun.post.domain.user.domain.entity

import baegteun.post.domain.feed.domain.entity.Feed
import baegteun.post.global.entity.BaseIdEntity
import baegteun.post.infrastructure.image.DefaultImage
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class User(
    @field:NotNull
    @Column(unique = true)
    @field:Size(max = 20)
    var userId: String,

    @field:NotNull
    @Column(unique = true)
    @field:Size(max = 20)
    var nickname: String,

    @field:NotNull
    @field:Size(max = 60)
    var password: String,

    @field:NotNull
    @field:Size(max = 100)
    @ColumnDefault("'${DefaultImage.PROFILE_IMAGE}'")
    var profileImageUrl: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var feeds: List<Feed> = listOf()
): BaseIdEntity()