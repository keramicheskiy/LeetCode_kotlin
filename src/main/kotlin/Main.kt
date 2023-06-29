import java.util.Calendar
import java.util.Random
import java.util.Scanner

fun main(args: Array<String>) {
//    val scanner = Scanner(System.`in`)
    var arabic = (1..100000).random().toString()
//    val arabic = "6785467"
//    arabic = scanner.nextInt().toString()
    var roman = arabic.arabicToLatin()
    println(arabic)
    println(roman)
    println(roman.latinToArabic())
}

operator fun String.times(b: Int): String {
    var result: String = ""
    for (i in 0..b-1) {
        result += this
    }
    return result
}


fun String.arabicToLatin(): String {
    val arabic = this
    var roman = ""
    var list: MutableList<Int> = mutableListOf()
    var resultList: MutableList<String> = mutableListOf()
    val listOfBuilders: List<Int> =
        listOf(1, 5, 10, 50, 100, 500, 1_000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000).sorted()
    val listOfExceptions: List<Int> =
        listOf(4, 9, 40, 90, 400, 900, 4_000, 9_000, 40_000, 90_000, 400_000, 900_000).sorted()

    val map: Map<Int, String> = mapOf(
        1 to "I",
        5 to "V",
        10 to "X",
        50 to "L",
        100 to "C",
        500 to "D",
        1_000 to "M",
        5_000 to "Ẏ",
        10_000 to "Ẋ",
        50_000 to "l",
        100_000 to "Ċ",
        500_000 to "Ḋ",
        1_000_000 to "Ṁ",
        4 to "IV",
        9 to "IX",
        40 to "XL",
        90 to "XC",
        400 to "CD",
        900 to "CM",
        4_000 to "MẎ",
        9_000 to "MẊ",
        40_000 to "Ẋl",
        90_000 to "ẊĊ",
        400_000 to "ĊḊ",
        900_000 to "ĊṀ"
    )



    for (i in arabic.length - 1 downTo 0) {
        var numIndex = arabic.length - 1 - i
        list.add((arabic.get(numIndex).toString() + "0" * i).toInt())
        resultList.add("")
    }
//  [10000, 4000, 800, 80, 9]
    for (num in list) {
        for (i in listOfBuilders.size - 1 downTo 0) {
            val divider = listOfBuilders.get(i)
            if (num % divider == 0 && num != 0) {
                val dividerMin = divider
                var dividerMax = 0
                if (i + 1 <= listOfBuilders.size - 1)
                    if (listOfBuilders.get(i + 1) <= num) {
                        dividerMax = listOfBuilders.get(i + 1)
                    } else {
                        dividerMax = dividerMin
                    }
                else
                    dividerMax = dividerMin

                var resultArabic = dividerMax
                var resultRoman = map.get(resultArabic)
                if (num == dividerMax) {
                    resultList.set(list.indexOf(num), map.get(num)!!)
                } else if (num in listOfExceptions) {
                    resultList.set(list.indexOf(num), map.get(num)!!)
                    break
                } else {
                    while (true) {
                        resultArabic += dividerMin
                        resultRoman += map.get(dividerMin)

                        if (resultArabic == num) {
                            resultList.set(list.indexOf(num), resultRoman!!)
                            break
                        }
                    }
                }
                break
            }
        }
    }
    println(resultList)
    for (i in 0..resultList.size - 1) {
        roman += resultList.get(i)
    }
    return roman;
}

fun String.latinToArabic(): Int {
    var latin = this
    var result = 0
    latin = latin
        .replace("IV", "IIII")
        .replace("IX", "VIIII")
        .replace("XL", "XXXX")
        .replace("XC", "LXXXX")
        .replace("CD", "CCCC")
        .replace("CM", "DCCCC")
        .replace("Mv", "MMMM")
        .replace("MẊ", "ẎMMMM")
        .replace("Ẋl", "ẊẊẊẊ")
        .replace("ẊĊ", "lẊẊẊẊ")
        .replace("ĊḊ", "ĊĊĊĊ")
        .replace("ĊṀ", "ḊĊĊĊĊ")
//        .replace("MV̄", "MMMM")
//        .replace("MX̄", "V̄MMMM")
//        .replace("X̄L̄", "X̄X̄X̄X̄")
//        .replace("X̄C̄", "L̄X̄X̄X̄X̄")
//        .replace("C̄D̄", "C̄C̄C̄C̄")
//        .replace("C̄M̄", "D̄C̄C̄C̄C̄")
    for (x in latin) {
        result += x.primitiveLatinToArabic()
    }
    return result
}

fun Char.primitiveLatinToArabic() : Int{
    return when (this) {
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        'M' -> 1000
        'Ẏ' -> 5_000
        'Ẋ' -> 10_000
        'l' -> 50_000
        'Ċ' -> 100_000
        'Ḋ' -> 500_000
        'Ṁ' -> 1_000_000
        else -> 0
    }
}

