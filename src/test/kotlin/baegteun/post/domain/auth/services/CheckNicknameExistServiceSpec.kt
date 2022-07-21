package baegteun.post.domain.auth.services

import baegteun.post.domain.user.domain.repository.UserRepository
import baegteun.post.domain.user.exception.AlreadyExistNicknameException
import baegteun.post.domain.user.exception.AlreadyExistUserIdException
import baegteun.post.domain.user.utils.impl.UserUtilImpl
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.HttpStatus

internal class CheckNicknameExistServiceSpec: DescribeSpec({
    val mockUserRepo = mockk<UserRepository>(relaxed = true)
    val userUtil = UserUtilImpl(mockUserRepo)
    val usecase = CheckNicknameExistService(userUtil)

    afterContainer {
        clearAllMocks()
    }

    describe("CheckNicknameExistService를") {
        context("저장된 데이터가 없을 떄 execute하면") {
            it("AlreadyExistNicknameException를 throw하지 않는다") {
                shouldNotThrow<AlreadyExistNicknameException> {
                    usecase.execute("a")
                }
            }
            it("StatusCode OK(200)을 방출한다") {
                val res = usecase.execute("a")

                res.statusCode shouldBe HttpStatus.OK
                res.statusCodeValue shouldBe 200
            }
        }
        context("저장된 데이터가 있고 그 데이터와 일치하지 않는 nickname으로 execute하면") {
            every { mockUserRepo.existsByNickname("b") } returns true
            it("AlreadyExistNicknameException throw하지 않는다") {
                shouldNotThrow<AlreadyExistNicknameException> {
                    usecase.execute("a")
                }
            }
            it("StatusCode OK(200)을 방출한다") {
                val res = usecase.execute("a")

                res.statusCode shouldBe HttpStatus.OK
                res.statusCodeValue shouldBe 200
            }
        }
        context("저장된 데이터가 있고 그 데이터와 일치하는 nickname으로 execute하면") {
            every { mockUserRepo.existsByNickname("a") } returns true
            it("AlreadyExistNicknameException을 throw한다") {
                shouldThrow<AlreadyExistNicknameException> {
                    usecase.execute("a")
                }
            }
        }
    }
})