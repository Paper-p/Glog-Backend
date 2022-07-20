package baegteun.post.domain.auth.presentation.dto

import baegteun.post.domain.auth.presentation.dto.request.SignupRequestDto
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

internal class SignupRequestDtoSpec: DescribeSpec({
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

    describe("SignupRequestDto") {
        context("nickname필드에 4자 미만 글자를 넣으면") {
            val dto = SignupRequestDto("A", "asdasf", "fdasdasa")
            it("nickname Size validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "nickname은 최소 4자, 최대 20자 입니다.")
            }
        }
        context("nickname필드에 20자 초과 글자를 넣으면") {
            val dto = SignupRequestDto("AsdfgAsdfgAsdfgAsdfgA", "asdasf", "fdasdasa")
            it("nickname Size validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "nickname은 최소 4자, 최대 20자 입니다.")
            }
        }
        context("userId필드에 4자 미만 글자를 넣으면") {
            val dto = SignupRequestDto("asdf", "", "asdfasdf")
            it("userId Size validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "userId는 최소 4자, 최대 20자 입니다.")
            }
        }
        context("userId필드에 20자 초과 글자를 넣으면") {
            val dto = SignupRequestDto("asdf", "AsdfgAsdfgAsdfgAsdfgA", "asdfasdf")
            it("userId Size validation 에러 메시지 방출") {
               checkErrorMessageValidation(dto, "userId는 최소 4자, 최대 20자 입니다.")
            }
        }
        context("password필드에 8자 미만 글자를 넣으면") {
            val dto = SignupRequestDto("asdf", "asdf", "a")
            it("password Size validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "password는 최소 8자, 최대 40자 입니다.")
            }
        }
        context("password필드에 40자 초과 글자를 넣으면") {
            val dto = SignupRequestDto("asdf", "asdf", "AsdfgAsdfgAsdfgAsdfgAsdfgAsdfgAsdfgAsdfgA")
            it("password Size validation 에러 메시지 방출") {
                checkErrorMessageValidation(dto, "password는 최소 8자, 최대 40자 입니다.")
            }
        }
    }
})
