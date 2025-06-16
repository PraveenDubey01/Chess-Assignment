package com.assignment.chess.model;

public class Position {
    private final int row;    // 0 to 7
    private final int column; // 0 to 7

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position fromChessNotation(String notation) {
        if (notation.length() != 2) return null;
        char colChar = notation.charAt(0);
        char rowChar = notation.charAt(1);

        int column = colChar - 'A';
        int row = 8 - Character.getNumericValue(rowChar);

        if (row < 0 || row > 7 || column < 0 || column > 7) return null;
        return new Position(row, column);
    }

    public String toChessNotation() {
        return "" + (char) ('A' + column) + (8 - row);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}