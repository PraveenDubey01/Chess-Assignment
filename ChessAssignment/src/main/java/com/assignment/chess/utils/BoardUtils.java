package com.assignment.chess.utils;

public class BoardUtils {
    
    private BoardUtils() {
    }
    public static boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}