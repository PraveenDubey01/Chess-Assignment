package com.assignment.chess.model;

public enum PieceType {
    PAWN, KING, QUEEN;

    public static PieceType fromString(String input) {
        try {
            return PieceType.valueOf(input.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}