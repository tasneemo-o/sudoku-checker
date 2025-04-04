import kotlin.math.sqrt

fun main() {

    // valid 4x4
    val valid4x4SudokuGrid = listOf(
        listOf("1", "-", "-", "-"),
        listOf("3", "-", "1", "2"),
        listOf("-", "1", "-", "-"),
        listOf("-", "3", "2", "1")
    )

    // invalid 6x6
    val invalid6x6SudokuGrid = listOf(
        listOf("1", "2", "3", "4", "5", "6"),
        listOf("4", "5", "6", "1", "2", "3"),
        listOf("2", "3", "4", "5", "6", "1"),
        listOf("5", "6", "1", "2", "3", "4"),
        listOf("3", "4", "5", "6", "1", "2"),
        listOf("6", "1", "2", "3", "4", "5")
    )

    // valid 9x9
    val valid9x9SudokuGrid = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        listOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )

    // valid customized 16x16
    // any squared number as a grid size is covered in this code: (valid for 9x9, 16x16, 25x25 etc.)
    val custom16x16SudokuGrid = listOf(
        listOf("1", "2", "3", "4", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"),
        listOf("-", "-", "-", "-", "5", "6", "7", "8", "-", "-", "-", "-", "-", "-", "-", "-"),
        listOf("-", "-", "-", "-", "-", "-", "-", "-", "9", "10", "11", "12", "-", "-", "-", "-"),
        listOf("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "13", "14", "15", "16"),
        listOf("5", "-", "-", "-", "9", "-", "-", "-", "13", "-", "-", "-", "1", "-", "-", "-"),
        listOf("6", "-", "-", "-", "10", "-", "-", "-", "14", "-", "-", "-", "2", "-", "-", "-"),
        listOf("7", "-", "-", "-", "11", "-", "-", "-", "15", "-", "-", "-", "3", "-", "-", "-"),
        listOf("8", "-", "-", "-", "12", "-", "-", "-", "16", "-", "-", "-", "4", "-", "-", "-"),
        listOf("-", "9", "-", "-", "-", "13", "-", "-", "-", "1", "-", "-", "-", "5", "-", "-"),
        listOf("-", "10", "-", "-", "-", "14", "-", "-", "-", "2", "-", "-", "-", "6", "-", "-"),
        listOf("-", "11", "-", "-", "-", "15", "-", "-", "-", "3", "-", "-", "-", "7", "-", "-"),
        listOf("-", "12", "-", "-", "-", "16", "-", "-", "-", "4", "-", "-", "-", "8", "-", "-"),
        listOf("-", "-", "13", "-", "-", "-", "1", "-", "-", "-", "5", "-", "-", "-", "9", "-"),
        listOf("-", "-", "14", "-", "-", "-", "2", "-", "-", "-", "6", "-", "-", "-", "10", "-"),
        listOf("-", "-", "15", "-", "-", "-", "3", "-", "-", "-", "7", "-", "-", "-", "11", "-"),
        listOf("-", "-", "16", "-", "-", "-", "4", "-", "-", "-", "8", "-", "-", "-", "12", "-")
    )

    // invalid: unequal dimensions
    val unequalDimensionsGrid = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        listOf("6", "-", "-", "1", "9", "5", "-", "-"),             // 8 elements
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "-")
    )

    // invalid: a row has duplicated values
    val invalidWithRowDuplicated = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        listOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        listOf("-", "-", "-", "4", "1", "9", "9", "-", "5"), // 9 is duplicated here
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "-")
    )

    // invalid: a column has duplicated values
    val invalidWithColDuplicated = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        listOf("6", "-", "-", "1", "9", "5", "-", "-", "-"), // 6 is duplicated in column 1
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "1", "-", "-", "-", "-", "2", "8", "-"),
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("6", "-", "-", "-", "8", "-", "-", "7", "9") // 6 is duplicated in column 1
    )

    // invalid: there are values out of bounds 1-n  (greater than n)
    val outOfBoundsGreaterThanN = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        listOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "10", "-", "2", "8", "-"), // 10 is out of bounds
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )

    // invalid: there are values out of bounds 1-n  (smaller than 1)
    val outOfBoundsSmallerThanN = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        listOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "-1", "-", "2", "8", "-"), // -1 is out of bounds
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )

    val invalidSubgrid9x9 = listOf(
        listOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),  // first 5 here
        listOf("6", "5", "-", "1", "9", "-", "-", "-", "-"),  // second 5 in same subgrid
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )
    val invalidSubgrid16x16 = listOf(
        listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "9", "11", "12", "13", "14", "15", "16"),  // Duplicate "9" in subgrid
        listOf("5", "6", "7", "8", "1", "2", "3", "4", "13", "14", "15", "16", "9", "10", "11", "12"),
        listOf("9", "10", "11", "12", "13", "14", "15", "16", "1", "2", "3", "4", "5", "6", "7", "8"),
        listOf("13", "14", "15", "16", "9", "10", "11", "12", "5", "6", "7", "8", "1", "2", "3", "4"),
        listOf("2", "1", "4", "3", "6", "5", "8", "7", "10", "9", "12", "11", "14", "13", "16", "15"),
        listOf("6", "5", "8", "7", "2", "1", "4", "3", "14", "13", "16", "15", "10", "9", "12", "11"),
        listOf("10", "9", "12", "11", "14", "13", "16", "15", "2", "1", "4", "3", "6", "5", "8", "7"),
        listOf("14", "13", "16", "15", "10", "9", "12", "11", "6", "5", "8", "7", "2", "1", "4", "3"),
        listOf("3", "4", "1", "2", "7", "8", "5", "6", "11", "12", "9", "10", "15", "16", "13", "14"),
        listOf("7", "8", "5", "6", "3", "4", "1", "2", "15", "16", "13", "14", "11", "12", "9", "10"),
        listOf("11", "12", "9", "10", "15", "16", "13", "14", "3", "4", "1", "2", "7", "8", "5", "6"),
        listOf("15", "16", "13", "14", "11", "12", "9", "10", "7", "8", "5", "6", "3", "4", "1", "2"),
        listOf("4", "3", "2", "1", "8", "7", "6", "5", "12", "11", "10", "9", "16", "15", "14", "13"),
        listOf("8", "7", "6", "5", "4", "3", "2", "1", "16", "15", "14", "13", "12", "11", "10", "9"),
        listOf("12", "11", "10", "9", "16", "15", "14", "13", "4", "3", "2", "1", "8", "7", "6", "5"),
        listOf("16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1")
    )
    val invalidInput = listOf(
        listOf("a", "3", "-", "-", ",", "-", "-", "-", "-"),   // a is invalid digit
        listOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        listOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        listOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        listOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        listOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        listOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        listOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        listOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )

    val gridWithEmptyCells = listOf(
        listOf("1", "-", "3", "4"),  // empty cell is represented by "-"
        listOf("3", "4", "1", "2"),
        listOf("-", "1", "4", "3"),
        listOf("4", "3", "2", "-")
    )

    val emptyGrid = mutableListOf<MutableList<String>>()

    checkSudoku(
        name = "a valid 4x4 sudoku grid",
        result = isValidSudoku(valid4x4SudokuGrid),
        correctResult = true
    )
    checkSudoku(
        name = "an invalid 6x6 sudoku grid",
        result = isValidSudoku(invalid6x6SudokuGrid),
        correctResult = false
    )
    checkSudoku(
        name = "a valid 9x9 sudoku grid",
        result = isValidSudoku(valid9x9SudokuGrid),
        correctResult = true
    )
    checkSudoku(
        name = "a valid 16x16 sudoku grid",
        result = isValidSudoku(custom16x16SudokuGrid),
        correctResult = true
    )
    checkSudoku(
        name = "a grid can be empty",
        result = isValidSudoku(emptyGrid),
        correctResult = true
    )
    checkSudoku(
        name = "the grid can contain empty cells",
        result = isValidSudoku(gridWithEmptyCells),
        correctResult = true
    )
    checkSudoku(
        name = "the number of rows should be equal to the number of columns",
        result = isValidSudoku(unequalDimensionsGrid),
        correctResult = false
    )
    checkSudoku(
        name = "a number appears in one row more than once",
        result = isValidSudoku(invalidWithRowDuplicated),
        correctResult = false
    )
    checkSudoku(
        name = "a number appears in one column more than once",
        result = isValidSudoku(invalidWithColDuplicated),
        correctResult = false
    )
    checkSudoku(
        name = "a number is out of bounds (greater than n)",
        result = isValidSudoku(outOfBoundsGreaterThanN),
        correctResult = false
    )
    checkSudoku(
        name = "a number is out of bounds (smaller than n)",
        result = isValidSudoku(outOfBoundsSmallerThanN),
        correctResult = false
    )
    checkSudoku(
        name = "a number appears in the same subgrid more than once",
        result = isValidSudoku(invalidSubgrid9x9),
        correctResult = false
    )
    checkSudoku(
        name = "a number appears in the same subgrid more than once",
        result = isValidSudoku(invalidSubgrid16x16),
        correctResult = false
    )
    checkSudoku(
        name = "invalid input",
        result = isValidSudoku(invalidInput),
        correctResult = false
    )
}

fun checkSudoku(name: String, result: Boolean, correctResult: Boolean) {
    val successColorText = "\u001B[32m"
    val failColorText = "\u001B[31m"
    val resetColor = "\u001B[0m"

    if (result == correctResult)
        println("$successColorText Success - $name$resetColor")
    else
        println("$failColorText Fail - $name$resetColor")
}

fun isValidSudoku(grid: List<List<String>>): Boolean {
    // check if the entire grid is empty
    if (grid.isEmpty())
        return true

    // check unequal dimensions
    for (i in 0..<grid.size) {
        if (grid[i].size != grid.size) {
            return false
        }
    }

    // check for invalid inputs
    for (i in grid.indices) {
        for (j in 0..<grid[i].size) {
            if (grid[i][j] != "-") {
                grid[i][j].toIntOrNull() ?: return false
            }
        }
    }

    // check if the size of the grid is squared or not
    // ex1: for 16 --> subgridSize = 4
    // so 4 * 4 = 16 then it's a squared number so 16 valid

    // ex2: for 6  --> subgridSize (double) = 2.--  => 2 (int)
    // so 2 * 2 = 4 != 6 then it's not a squared number so 6 not valid
    val subgridSize = sqrt(grid.size.toDouble()).toInt()
    if (subgridSize * subgridSize != grid.size) {
        return false
    }
    // check for subgrid duplicates
    for (subgridRow in 0..<subgridSize) {
        for (subgridCol in 0..<subgridSize) {
            val visited = mutableSetOf<String>()

            for (i in 0..<subgridSize) {
                for (j in 0..<subgridSize) {
                    // actual subgrid row and column
                    val row = subgridRow * subgridSize + i        // actually for this law i didn't get it fully by myself,
                    val col = subgridCol * subgridSize + j        // i took some help here

                    if (grid[row][col] != "-") {
                        if (grid[row][col] in visited) {
                            return false
                        }
                        visited.add(grid[row][col])
                    }
                }
            }
        }
    }

    // check for row duplicates
    for (i in grid.indices) {
        val visited = mutableSetOf<String>()
        for (j in 0..<grid[i].size) {
            if (grid[i][j] != "-") {
                if (grid[i][j] in visited) {
                    return false
                }
                visited.add(grid[i][j])
            }

        }
    }

    // check for column duplicates
    for (i in grid.indices) {
        val visited = mutableSetOf<String>()
        for (j in 0..<grid[i].size) {
            if (grid[j][i] != "-") {
                if (grid[j][i] in visited) {
                    return false
                }
                visited.add(grid[j][i])
            }

        }
    }

    // check if any number is out of bounds
    for (i in grid.indices) {
        for (j in 0..<grid[i].size) {
            if (grid[i][j] != "-") {
                val num = grid[i][j].toInt()
                if (num < 1 || num > grid.size) {
                    return false
                }
            }
        }
    }
    return true
}
