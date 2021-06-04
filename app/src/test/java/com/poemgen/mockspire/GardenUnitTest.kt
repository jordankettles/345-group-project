package com.poemgen.mockspire

import android.util.Log
import com.poemgen.mockspire.poemgenerator.record.Garden
import com.poemgen.mockspire.poemgenerator.record.Poem
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Tests for Garden.kt
 *
 * Tests implemented:
 *  - addDummyPoems
 *      Ensure poems are added to the garden correctly.
 */
class GardenUnitTest {

    /**
     * Test that poems are added to the garden correctly.
     */
    @Test
    fun addDummyPoems() {
        val poem1 = Poem("whoah", "Alas, poor Yorek, he died a camel.")
        val poem2 = Poem("Wew lad", "Stuff happened. Wew. ")
        val poem3 = Poem("Brigalow", "Bring me my coffee, said I. But he did not. What shame. He is now dead. ")
        Garden.seeds.add(poem1)
        Garden.seeds.add(poem2)
        Garden.seeds.add(poem3)

        assertEquals(Garden.seeds[0], poem1)
        assertEquals(Garden.seeds[1], poem2)
        assertEquals(Garden.seeds[2], poem3)
    }
}