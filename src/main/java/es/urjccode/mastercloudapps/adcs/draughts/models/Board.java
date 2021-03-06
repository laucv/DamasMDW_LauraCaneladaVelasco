package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board {

    private Piece[][] pieces;

    private final int MAXIMUM_LIMIT_TO_JUMP = Coordinate.getDimension() - 2;

    private final int MINIMUM_LIMIT_TO_JUMP = 1;

    Board() {
        this.pieces = new Piece[Coordinate.getDimension()][Coordinate.getDimension()];
        for (int i = 0; i < Coordinate.getDimension(); i++)
            for (int j = 0; j < Coordinate.getDimension(); j++)
                this.pieces[i][j] = null;
    }

    Piece getPiece(Coordinate coordinate) {
        assert coordinate != null;
        return this.pieces[coordinate.getRow()][coordinate.getColumn()];
    }

    void put(Coordinate coordinate, Piece piece) {
        this.pieces[coordinate.getRow()][coordinate.getColumn()] = piece;
    }

    Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        Piece piece = this.getPiece(coordinate);
        this.put(coordinate, null);
        return piece;
    }

    void move(Coordinate origin, Coordinate target) {
        assert this.getPiece(origin) != null;
        this.put(target, this.remove(origin));
    }

    List<Piece> getBetweenDiagonalPieces(Coordinate origin, Coordinate target) {
        List<Piece> betweenDiagonalPieces = new ArrayList<Piece>();
        if (origin.isOnDiagonal(target))
            for (Coordinate coordinate : origin.getBetweenDiagonalCoordinates(target)) {
                Piece piece = this.getPiece(coordinate);
                if (piece != null)
                    betweenDiagonalPieces.add(piece);
            }
        return betweenDiagonalPieces;
    }

    int getAmountBetweenDiagonalPieces(Coordinate origin, Coordinate target) {
        if (!origin.isOnDiagonal(target))
            return 0;
        int betweenDiagonalPieces = 0;
        for (Coordinate coordinate : origin.getBetweenDiagonalCoordinates(target))
            if (this.getPiece(coordinate) != null)
                betweenDiagonalPieces++;
        return betweenDiagonalPieces;
    }

    Color getColor(Coordinate coordinate) {
        final Piece piece = this.getPiece(coordinate);
        if (piece == null)
            return null;
        return piece.getColor();
    }

    boolean isEmpty(Coordinate coordinate) {
        return this.getPiece(coordinate) == null;
    }

    List<Coordinate> getAvailablePiecesToJump(Color color, List<Coordinate> coordinates) {
        List<Coordinate> availablePiecesToJump = new ArrayList<Coordinate>();
        for (Coordinate coordinate : coordinates) {
            this.checkDiagonals(availablePiecesToJump, color, coordinate);
        }
        return availablePiecesToJump;
    }

    void checkDiagonals(List<Coordinate> availablePiecesToJump, Color color, Coordinate coordinate) {
        if (color.equals(Color.WHITE) && coordinate.getRow() > MINIMUM_LIMIT_TO_JUMP) {
            if (coordinate.getColumn() < MAXIMUM_LIMIT_TO_JUMP && jumpIsPossible(coordinate, Direction.SE))
                    availablePiecesToJump.add(coordinate);
            if (coordinate.getColumn() > MINIMUM_LIMIT_TO_JUMP && jumpIsPossible(coordinate, Direction.SW))
                    availablePiecesToJump.add(coordinate);
        }
        if (color.equals(Color.BLACK) && coordinate.getRow() < MAXIMUM_LIMIT_TO_JUMP) {
            if (coordinate.getColumn() > MINIMUM_LIMIT_TO_JUMP && jumpIsPossible(coordinate, Direction.NW))
                    availablePiecesToJump.add(coordinate);
            if (coordinate.getColumn() < MAXIMUM_LIMIT_TO_JUMP && jumpIsPossible(coordinate, Direction.NE))
                    availablePiecesToJump.add(coordinate);
        }
    }

    boolean jumpIsPossible(Coordinate coordinate, Direction direction) {
        return this.getPiece(coordinate.getDiagonalCoordinate(direction, 1)) != null
            && !this.getColor(coordinate.getDiagonalCoordinate(direction, 1)).equals(this.getColor(coordinate))
            && this.getPiece(coordinate.getDiagonalCoordinate(direction, 2)) == null;
    }

    int getNumberOfPieces(Color color) {
        int result = 0;
        for (int i = 0; i < Coordinate.getDimension(); i++)
            for (int j = 0; j < Coordinate.getDimension(); j++)
                if (this.pieces[i][j] != null)
                    if (this.pieces[i][j].color.equals(color))
                        result++;
        return result;
    }

    @Override
    public String toString() {
        String string = "";
        string += this.toStringHorizontalNumbers();
        for (int i = 0; i < Coordinate.getDimension(); i++)
            string += this.toStringHorizontalPiecesWithNumbers(i);
        string += this.toStringHorizontalNumbers();
        return string;
    }

    private String toStringHorizontalNumbers() {
        String string = " ";
        for (int j = 0; j < Coordinate.getDimension(); j++)
            string += j;
        return string + "\n";
    }

    private String toStringHorizontalPiecesWithNumbers(int row) {
        String string = " " + row;
        for (int j = 0; j < Coordinate.getDimension(); j++) {
            Piece piece = this.getPiece(new Coordinate(row, j));
            if (piece == null)
                string += " ";
            else {
                string += piece;
            }
        }
        return string + row + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(pieces);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        if (!Arrays.deepEquals(pieces, other.pieces))
            return false;
        return true;
    }

}
