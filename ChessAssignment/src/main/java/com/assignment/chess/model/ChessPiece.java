package com.assignment.chess.model;

public class ChessPiece {
    private final PieceType type;
    private final Position position;

    public ChessPiece(PieceType type, Position position) {
        this.type = type;
        this.position = position;
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }
}
