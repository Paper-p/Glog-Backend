package baegteun.post.domain.auth.presentation.dto

import baegteun.post.domain.auth.presentation.dto.request.SigninRequestDto
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

internal class SigninRequestDtoSpec: DescribeSpec({
    val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    val validator: Validator = factory.validator

    fun <T>checkErrorMessageValidation(dto: T, message: String) {
        val violations = validator.validate(dto)
        violations.size shouldNotBe 0
        violations.forEach {
            it.message shouldBe message
        }
    }

    afterEach {
        factory.close()
    }

    describe("SigninRequestDto") {
        context("userId 필드에 공백을 넣으면") {
            val dto = SigninRequestDto("", "asdf")
            it("userId NotBlank validation 에러 에시지 방출") {
                checkErrorMessageValidation(dto, "userId는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
            }
        }
        context("userId 필드에 WhiteSpace를 넣으면") {
            val dto = SigninRequestDto(" ", "asdf")
            it("userid NotBlank validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "userId는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
            }
        }
        context("password 필드에 공백을 넣으면") {
            val dto = SigninRequestDto("asdf", "")
            it("password NotBlank validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
            }
        }
        context("password 필드에 WhiteSpace를 넣으면") {
            val dto = SigninRequestDto("asdf", " ")
            it("password NotBlank validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
            }
        }
    }
})
