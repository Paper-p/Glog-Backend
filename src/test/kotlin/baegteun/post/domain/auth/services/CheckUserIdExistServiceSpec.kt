package baegteun.post.domain.auth.services

import baegteun.post.domain.user.domain.repository.UserRepository
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

internal class CheckUserIdExistServiceSpec: DescribeSpec({
    val mockUserRepo = mockk<UserRepository>(relaxed = true)
    val userUtil = UserUtilImpl(mockUserRepo)
    val usecase = CheckUserIdExistService(userUtil)

    afterContainer {
        clearAllMocks()
    }

    describe("CheckUserIdExistService를") {
        context("저장된 데이터가 없을 때 execute하면") {
            it("AlreadyExistUserIdException을 throw하지 않는다") {
                shouldNotThrow<AlreadyExistUserIdException> {
                    usecase.execute("a")
                }
            }
            it("StatusCode OK(200)을 방출한다") {
                val res = usecase.execute("a")

                res.statusCode shouldBe HttpStatus.OK
                res.statusCodeValue shouldBe 200
            }
        }
        context("저장된 데이터가 있고 그 데이터와 일치하지 않는 userId로 execute하면") {
            every { mockUserRepo.existsByUserId("b") } returns true
            it("AlreadyExistUserIdException을 throw하지 않는다") {
                shouldNotThrow<AlreadyExistUserIdException> {
                    usecase.execute("a")
                }
            }
            it("StatusCode OK(200)을 방출한다") {
                val res = usecase.execute("a")

                res.statusCode shouldBe HttpStatus.OK
                res.statusCodeValue shouldBe 200
            }
        }
        context("저장된 데이터가 있고 그 데이터와 일치하는 userId로 execute하면") {
            every { mockUserRepo.existsByUserId("a") } returns true
            it("AlreadyExistUserIdException을 throw한다") {
                shouldThrow<AlreadyExistUserIdException> {
                    usecase.execute("a")
                }
            }
        }
    }
})