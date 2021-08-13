package com.poemgen.deeppoet

import com.poemgen.deeppoet.util.Head
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Tests for Head.kt
 *
 * Adds and retrieves random int from list.
 */
class HeadUnitTest {

    @Test
    fun retrieveHead() {
        var head = Head(mutableListOf(1, 5, 4), mutableListOf(2, 8, 9))

        var val1 = head.getRandomIdle()
        var test1 = (val1 == 1) or
                    (val1 == 5) or
                    (val1 == 4)

        var val2 = head.getRandomTalking()
        var test2 = (val2 == 2) or
                    (val2 == 8) or
                    (val2 == 9)

        var val3 = head.getRandomIdle()
        var test3 = (val3 == 3) or
                    (val3 == 6) or
                    (val3 == 7)

        var val4 = head.getRandomTalking()
        var test4 = (val4 == 3) or
                    (val4 == 6) or
                    (val4 == 7)


        assertEquals(test1, true)
        assertEquals(test2, true)
        assertEquals(test3, false)
        assertEquals(test4, false)

    }

}