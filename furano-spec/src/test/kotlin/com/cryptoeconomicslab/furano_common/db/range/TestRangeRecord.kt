package com.cryptoeconomicslab.furano_common.db.range

import com.cryptoeconomicslab.furano_common.error.UnexpectedParamsException
import com.cryptoeconomicslab.furano_core.types.convertFromString
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.properties.Delegates

class TestRangeRecord {

    class TestIntersect {

        lateinit var target: RangeRecord
        var expected: Boolean by Delegates.notNull()

        // given
        var start: BigInteger by Delegates.notNull()
        var end: BigInteger by Delegates.notNull()

        @Before
        fun setUp() {
            target = RangeRecord(
                start = BigInteger.valueOf(100),
                end = BigInteger.valueOf(200),
                value = convertFromString("Hello World")
            )
        }

        @Test
        fun `given (start, end) = (0, 50), expected to return false`() {
            // given
            start = BigInteger.valueOf(0)
            end = BigInteger.valueOf(50)

            // expected
            expected = false

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 100), expected to return false`() {
            // given
            start = BigInteger.valueOf(0)
            end = BigInteger.valueOf(100)

            // expected
            expected = false

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 150), expected to return true`() {
            // given
            start = BigInteger.valueOf(0)
            end = BigInteger.valueOf(150)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 200), expected to return true`() {
            // given
            start = BigInteger.valueOf(0)
            end = BigInteger.valueOf(200)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (0, 300), expected to return true`() {
            // given
            start = BigInteger.valueOf(0)
            end = BigInteger.valueOf(300)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (100, 200), expected to return true`() {
            // given
            start = BigInteger.valueOf(100)
            end = BigInteger.valueOf(200)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (100, 150), expected to return true`() {
            // given
            start = BigInteger.valueOf(100)
            end = BigInteger.valueOf(150)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (100, 300), expected to return true`() {
            // given
            start = BigInteger.valueOf(100)
            end = BigInteger.valueOf(300)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (150, 200), expected to return true`() {
            // given
            start = BigInteger.valueOf(150)
            end = BigInteger.valueOf(200)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (150, 300), expected to return true`() {
            // given
            start = BigInteger.valueOf(150)
            end = BigInteger.valueOf(300)

            // expected
            expected = true

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (200, 300), expected to return false`() {
            // given
            start = BigInteger.valueOf(200)
            end = BigInteger.valueOf(300)

            // expected
            expected = false

            // assert
            val actual = target.intersect(start, end)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `given (start, end) = (-100, 0), expected to throw exception when start is negative`() {
            // given
            start = BigInteger.valueOf(-100)
            end = BigInteger.valueOf(0)

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
            start = BigInteger.valueOf(-200)
            end = BigInteger.valueOf(-100)

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
            start = BigInteger.valueOf(200)
            end = BigInteger.valueOf(100)

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
