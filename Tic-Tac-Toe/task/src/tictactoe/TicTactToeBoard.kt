package tictactoe

import java.lang.NumberFormatException
import kotlin.math.abs


class TicTactToeBoard(var cells: String) {
    private val board = "[OX ]{3}".toRegex().findAll(cells).map { it.value.toCharArray() }.toList()


    private fun printBoard() {
        LINE_HYPHEN.let(::println)
        board.map { it.joinToString(" ") }.map(LINE_PLAY).forEach(::println)
        LINE_HYPHEN.let(::println)
    }

    private fun putTo(token: Char) {
        var rightInput = false
        while (!rightInput){
            ENTER_COORD.let(::println)

            try{
                val (y, x) = readLine()!!.split(" ").map{ it.toInt().minus(1) }
                if (listOf(y, x).any{ it > 2 }) throw BadCoordinatesException()

                if(board[y][x] == EMPTY_TOKEN){
                    board[y][x] = token
                    cells = board.joinToString("") { it.joinToString("") }
                    rightInput = true
                }else{
                    throw OccupiedCellException()
                }
            }catch (numFormat: NumberFormatException){
                ERROR_SHOULD_NUMS.let(::println)
            }catch (badCoord: BadCoordinatesException){
                badCoord.message.let(::println)
            }catch (occupied: OccupiedCellException){
                occupied.message.let(::println)
            }
        }

    }

    private fun whatsUpInTheField(): String {
        val isMoreThanOneDifference =
            abs(cells.count { it == PLAYER_ONE_TOKEN } - cells.count { it == PLAYER_TWO_TOKEN }) > 1
        val areEmptyFields = cells.count { it == EMPTY_TOKEN } > 0
        val isXInARow = isInARow(PLAYER_ONE_TOKEN)
        val isOInARow = isInARow(PLAYER_TWO_TOKEN)

        return if (isMoreThanOneDifference) {
            MSG_IMPOSSIBLE
        } else if (isXInARow) {
            if (isOInARow) {
                MSG_IMPOSSIBLE
            } else {
                MSG_X_WINS
            }
        } else if (isOInARow) {
            if (isXInARow) {
                MSG_IMPOSSIBLE
            } else {
                MSG_O_WINS
            }
        } else if (areEmptyFields) {
            MSG_NOT_FINISH
        } else if (!areEmptyFields) {
            MSG_DRAW
        }else{
            ""
        }
    }

    private fun isInARow(token: Char): Boolean {
        val horizontalSearching = board.any { line -> line.all { it == token } }
        var verticalSearching = false
        var diagonalSearching = false
        var inverseDiagonalSearching = false

        for (column in 0 until 3) {
            var line = ""
            for (row in board.indices) {
                line += board[row][column]
                verticalSearching = line.all { it == token } && line.length == board[row].size
            }

            if (verticalSearching) break
        }

        var line = ""
        for (diagonal in board.indices) {
            line += board[diagonal][diagonal]
            diagonalSearching = line.all { it == token } && line.length == board.size

            if (diagonalSearching) break
        }

        line = ""
        for (row in board.indices) {
            for (column in board[row].size.minus(1) downTo 0) {
                if (row + column == board.size.minus(1)) line += board[row][column]
                inverseDiagonalSearching = line.all { it == token } && line.length == board[row].size
            }

            if (inverseDiagonalSearching) break
        }

        return horizontalSearching || verticalSearching || diagonalSearching || inverseDiagonalSearching
    }

    fun play(){
        var gameOver = false
        var turn = 1
        printBoard()
        while (!gameOver){
            val token = if (turn % 2 == 0) PLAYER_TWO_TOKEN else PLAYER_ONE_TOKEN
            putTo(token)
            printBoard()
            val fieldState = whatsUpInTheField()
            if (listOf(MSG_X_WINS, MSG_O_WINS, MSG_DRAW).any { it == fieldState }) {
                gameOver = true
                fieldState.let(::println)
            }
            turn++
        }
    }
}