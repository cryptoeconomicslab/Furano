package com.cryptoeconomicslab.furano_common.db.range

import com.cryptoeconomicslab.furano_common.error.UnexpectedParamsException
import com.cryptoeconomicslab.furano_common.types.convertFromString
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.nio.charset.Charset
import kotlin.properties.Delegates

class TestRangeRecord {

    class TestEncode {

        lateinit var target: RangeRecord

        @Before
        fun setUp() {
            target = RangeRecord(
                start = 100,
                end = 200,
                value = convertFromString("Hello World")
            )
        }

        @Test
        fun `given normal params, expected to be encoded`() {
            // given
            val encodedTargetInString = target.encode().toString(Charset.defaultCharset())

            // expected
            val expected = """
            {
                "start" : 100,
                "end" : 200,
                "value" : Hello World
            }
            """.trimIndent()

            // assert
            assertThat(encodedTargetInString).isEqualTo(expected)
        }
    }

    class TestIntersect {

        lateinit var target: RangeRecord
        var expected: Boolean by Delegates.notNull()

        // given
        var start: Long by Delegates.notNull()
        var end: Long by Delegates.notNull()

        @Before
        fun setUp() {
            target = RangeRecord(
                start = 100,
                end = 200,
                value = convertFromString("Hello World")
            )
        }

        @Test
        fun `given (start, end) = (0, 50), expected to return false`() {
            // given
            start = 0
            end = 50

            // expected
            expected = false

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 100), expected to return false`() {
            // given
            start = 0
            end = 100

            // expected
            expected = false

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 150), expected to return true`() {
            // given
            start = 0
            end = 150

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 200), expected to return true`() {
            // given
            start = 0
            end = 200

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 300), expected to return true`() {
            // given
            start = 0
            end = 300

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (100, 200), expected to return true`() {
            // given
            start = 100
            end = 200

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (100, 150), expected to return true`() {
            // given
            start = 100
            end = 150

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (100, 300), expected to return true`() {
            // given
            start = 100
            end = 300

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (150, 200), expected to return true`() {
            // given
            start = 150
            end = 200

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (150, 300), expected to return true`() {
            // given
            start = 150
            end = 300

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (200, 300), expected to return false`() {
            // given
            start = 200
            end = 300

            // expected
            expected = false

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (-100, 0), expected to throw exception when start is negative`() {
            // given
            start = -100
            end = 0

            // assert
            try {
                target.intersect(start, end)
                fail("should throw exception")
            } catch (e: UnexpectedParamsException) {
                assertTrue(true)
            } catch (e: Exception) {
                fail("expected UnexpectedParamsException to be thrown, actually $e was thrown")
            }
        }

        @Test
        fun `given (start, end) = (-200, -100), expected to throw exception when end is negative`() {
            // given
            start = -200
            end = -100

            // assert
            try {
                target.intersect(start, end)
                fail("should throw exception")
            } catch (e: UnexpectedParamsException) {
                assertTrue(true)
            } catch (e: Exception) {
                fail("expected UnexpectedParamsException to be thrown, actually $e was thrown")
            }
        }

        @Test
        fun `given (start, end) = (200, 100), expected to throw exception when end is greater than start`() {
            // given
            start = 200
            end = 100

            // assert
            try {
                target.intersect(start, end)
                fail("should throw exception")
            } catch (e: UnexpectedParamsException) {
                assertTrue(true)
            } catch (e: Exception) {
                fail("expected UnexpectedParamsException to be thrown, actually $e was thrown")
            }
        }
    }
}
