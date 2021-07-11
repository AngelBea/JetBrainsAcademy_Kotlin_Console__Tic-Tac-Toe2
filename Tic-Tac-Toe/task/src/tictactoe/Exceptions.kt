package tictactoe

import java.lang.Exception

class BadCoordinatesException : Exception(){
    override val message: String?
        get() = ERROR_COORD_NUM
}

class OccupiedCellException: Exception(){
    override val message: String?
        get() = ERROR_CELL_OCCUPIED
}