package com.poemgen.deeppoet

import com.poemgen.deeppoet.util.Head
import com.poemgen.deeppoet.util.HeadCollection
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Tests for Head.kt
 *
 * Adds and retrieves random int from list.
 */
class HeadUnitTest {

    /**
     * Creates a head instance. Makes sure int returned by methods are from the list and not anything else.
     */
    @Test
    fun retrieveHead() {
        var testHead1 = Head("TestHead1", mutableListOf(1, 5, 4), mutableListOf(2, 8, 9))
        var testHead2 = Head("TestHead2", mutableListOf(2, 3, 8), mutableListOf(5, 6, 7))
        HeadCollection.heads = mutableListOf()
        HeadCollection.heads.add(testHead1)
        HeadCollection.heads.add(testHead2)

        // Testing First Head
        HeadCollection.setSelectedHead(0)

        var val1 = HeadCollection.getSelectedHead().getRandomIdle()
        var test1 = (val1 == 1) or
                    (val1 == 5) or
                    (val1 == 4)

        var val2 = HeadCollection.getSelectedHead().getRandomTalking()
        var test2 = (val2 == 2) or
                    (val2 == 8) or
                    (val2 == 9)

        var val3 = HeadCollection.getSelectedHead().getRandomIdle()
        var test3 = (val3 != 1) and
                    (val3 != 5) and
                    (val3 != 4)

        var val4 = HeadCollection.getSelectedHead().getRandomTalking()
        var test4 = (val4 != 2) and
                    (val4 != 8) and
                    (val4 != 9)

        assertEquals(HeadCollection.getSelectedHead().getName(), "TestHead1")

        assertEquals(test1, true)
        assertEquals(test2, true)
        assertEquals(test3, false)
        assertEquals(test4, false)

        // Testing Second Head
        HeadCollection.setSelectedHead(1)

        var val5 = HeadCollection.getSelectedHead().getRandomIdle()
        var test5 = (val5 == 2) or
                (val5 == 3) or
                (val5 == 8)

        var val6 = HeadCollection.getSelectedHead().getRandomTalking()
        var test6 = (val6 == 5) or
                (val6 == 6) or
                (val6 == 7)

        var val7 = HeadCollection.getSelectedHead().getRandomIdle()
        var test7 = (val7 != 2) and
                (val7 != 3) and
                (val7 != 8)

        var val8 = HeadCollection.getSelectedHead().getRandomTalking()
        var test8 = (val8 != 5) and
                (val8 != 6) and
                (val8 != 7)

        assertEquals(HeadCollection.getSelectedHead().getName(), "TestHead2")

        assertEquals(test5, true)
        assertEquals(test6, true)
        assertEquals(test7, false)
        assertEquals(test8, false)
    }

}