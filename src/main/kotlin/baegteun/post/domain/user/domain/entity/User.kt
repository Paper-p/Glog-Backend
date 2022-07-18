package baegteun.post.domain.user.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

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
    var profileImageUrl: String
)