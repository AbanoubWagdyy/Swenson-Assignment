# Swenson-Assignment

I.((3+1)/3)*9 = 12

-------------------------------------------------------------------------------------------------------

II.

fun isAnagram(str1: String, str2: String): Boolean {

    val charactersMap = HashMap<Char, Int>()
    for (ch in str1.toCharArray())
        if (charactersMap.containsKey(ch))
            charactersMap[ch] = charactersMap[ch]!! + 1
        else
            charactersMap[ch] = 1


    for (ch in str2.toCharArray()) {
        if (!charactersMap.containsKey(ch)) {
            return false
        } else {
            charactersMap[ch] = charactersMap[ch]!! - 1
            if (charactersMap[ch] == 0) {
                charactersMap.remove(ch)
            }
        }
    }
    return true
}

fun main(args: Array<String>) {
    var str1 = "mother in law";
    var str2 = "woman hitler";

    if (isAnagram(str1, str2))
        println("Anagrams")
    else
        println("Not Anagrams")

     str1 = "LISTEN";
     str2 = "SILENT";

    if (isAnagram(str1, str2))
        println("Anagrams")
    else
        println("Not Anagrams")

    str1 = "mother";
    str2 = "theremore";

    if (isAnagram(str1, str2))
        println("Anagrams")
    else
        println("Not Anagrams")
}

--------------------------------------------------------------------------------------------------------------------

III.
a)recursive approach : 

b)iterative approach : 

fun fibonacci(): Sequence<Int> {
    return generateSequence(Pair(1, 1), { Pair(it.second, it.first + it.second) }).map { it.first }
}

fun main(args: Array<String>) {
	println(fibonacci().take(7).toList())
    println(fibonacci().take(9).toList())
}
