package com.poemgen.mockspire.tokenization

/**
 * The GPT2Tokenizer class encodes strings into tokens and decodes tokens to strings.
 * Tokens are understood by the GPT2 model and are used as input to generate text, not Strings.
 * This means that the output of GPT2 is also tokens, which then needs to be decoded into a String.
 * @property encoder Pairings of Strings and Tokens.
 * @property decoder Pairings of Tokens and Strings.
 * @property bpeRanks Byte-Pair encoding ranks.
 * @property encodeRegex Regex.
 */
class GPT2Tokenizer(
        private val encoder: Map<String, Int>,
        private val decoder: Map<Int, String>,
        private val bpeRanks: Map<Pair<String, String>, Int>) {
    private val encodeRegex = Regex("""'s|'t|'re|'ve|'m|'ll|'d| ?\p{L}+| ?\p{N}+| ?[^\s\p{L}\p{N}]+|\s+(?!\S)|\s+""")

    /**
     * Decodes Tokens to Strings.
     * @param tokens Tokens to decode.
     * @return String decoded tokens as a String.
     */
    fun decode(tokens: List<Int>): String {
        val text = tokens.joinToString("") { decoder.getOrDefault(it, "") }
        val utfCodepoints = text.map { byteDecoder[it.toString()]!! }
        return String(utfCodepoints.toIntArray(), 0, utfCodepoints.size)
    }

    /**
     * Encodes Strings to tokens.
     * @param text String to encode.
     * @return MutableList<Int> Encoded string as tokens.
     */
    fun encode(text: String): MutableList<Int> {
        val tokens = encodeRegex.findAll(text).map { result ->
            result.value.codePoints()
                    .boxed()
                    .map { byteEncoder[it]!! }
                    .toArray()
                    .joinToString("")
        }

        return tokens
                .map { bpe(it) }
                .flatten()
                .map { encoder[it]!! }
                .toMutableList()
    }

    private fun bpe(token: String): List<String> {
        if (token.length <= 1) return listOf(token)

        var word = token.map { it.toString() }
        var pairs = getPairs(word)

        while (true) {
            if (!pairs.any { bpeRanks.containsKey(it) }) break
            val (first, second) = pairs.minBy { bpeRanks.getOrDefault(it, Int.MAX_VALUE) } ?: break

            var i = 0
            val newWord = mutableListOf<String>()
            while (i < word.size) {
                val j = word.withIndex().indexOfFirst { it.index >= i && it.value == first }
                if (j != -1) {
                    newWord.addAll(word.subList(i, j))
                    i = j
                } else {
                    newWord.addAll(word.subList(i, word.size))
                    break
                }

                if (word[i] == first && i < word.size-1 && word[i+1] == second) {
                    newWord.add(first+second)
                    i += 2
                } else {
                    newWord.add(word[i])
                    i += 1
                }
            }

            word = newWord
            if (word.size == 1) {
                break
            } else {
                pairs = getPairs(word)
            }
        }

        return word
    }

    private fun getPairs(word: List<String>): Set<Pair<String, String>> {
        return mutableSetOf<Pair<String, String>>().apply {
            for (i in 0 until word.size-1) {
                add(word[i] to word[i+1])
            }
        }
    }
}
