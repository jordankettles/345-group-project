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

        var test1 = (head.getRandomIdle() == 1) or
                    (head.getRandomIdle() == 5) or
                    (head.getRandomIdle() == 4)

        var test2 = (head.getRandomTalking() == 2) or
                    (head.getRandomTalking() == 8) or
                    (head.getRandomTalking() == 9)

        var test3 = (head.getRandomIdle() == 3) or
                    (head.getRandomIdle() == 6) or
                    (head.getRandomIdle() == 7)

        var test4 = (head.getRandomTalking() == 3) or
                    (head.getRandomTalking() == 6) or
                    (head.getRandomTalking() == 7)


        assertEquals(test1, true)
        assertEquals(test2, true)
        assertEquals(test3, false)
        assertEquals(test4, false)

    }

}