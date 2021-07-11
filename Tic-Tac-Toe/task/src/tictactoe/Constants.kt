package tictactoe

const val PLAYER_ONE_TOKEN = 'X'
const val PLAYER_TWO_TOKEN = 'O'
const val EMPTY_TOKEN = ' '
const val ENTER_CELLS = "Enter cells:"
const val ENTER_COORD = "Enter the coordinates:"
val LINE_HYPHEN = "-".repeat(9)
val EMPTY_BOARD = EMPTY_TOKEN.toString().repeat(9)
val LINE_PLAY = {line: String -> "| $line |"}

//Messages
const val MSG_IMPOSSIBLE = "Impossible"
const val MSG_DRAW = "Draw"
const val MSG_X_WINS = "X wins"
const val MSG_O_WINS = "O wins"
const val MSG_NOT_FINISH = "Game not finished"

const val ERROR_SHOULD_NUMS = "You should enter numbers!"
const val ERROR_COORD_NUM = "Coordinates should be from 1 to 3!"
const val ERROR_CELL_OCCUPIED = "This cell is occupied! Choose another one!"