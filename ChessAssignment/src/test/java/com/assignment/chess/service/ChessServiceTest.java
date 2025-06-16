package com.assignment.chess.service;

import com.assignment.chess.model.ChessPiece;
import com.assignment.chess.model.PieceType;
import com.assignment.chess.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ChessServiceTest {
    private ChessService chessService;

    @BeforeEach
    void setUp() {
        chessService = new ChessService();
    }

    @Test
    void testPawnMoves() {
        // Test pawn at starting position
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, new Position(6, 0));
        List<String> moves = chessService.getPossibleMoves(pawn);
        assertEquals(1, moves.size());
        assertTrue(moves.contains("a5"));

        // Test pawn at edge of board
        pawn = new ChessPiece(PieceType.PAWN, new Position(0, 0));
        moves = chessService.getPossibleMoves(pawn);
        assertEquals(0, moves.size());
    }

    @Test
    void testKingMoves() {
        // Test king in middle of board
        ChessPiece king = new ChessPiece(PieceType.KING, new Position(4, 4));
        List<String> moves = chessService.getPossibleMoves(king);
        assertEquals(8, moves.size());
        assertTrue(moves.contains("e4"));
        assertTrue(moves.contains("e6"));
        assertTrue(moves.contains("d5"));
        assertTrue(moves.contains("f5"));
        assertTrue(moves.contains("d4"));
        assertTrue(moves.contains("f4"));
        assertTrue(moves.contains("d6"));
        assertTrue(moves.contains("f6"));

        // Test king at corner
        king = new ChessPiece(PieceType.KING, new Position(0, 0));
        moves = chessService.getPossibleMoves(king);
        assertEquals(3, moves.size());
        assertTrue(moves.contains("b1"));
        assertTrue(moves.contains("a2"));
        assertTrue(moves.contains("b2"));
    }

    @Test
    void testQueenMoves() {
        // Test queen in middle of board
        ChessPiece queen = new ChessPiece(PieceType.QUEEN, new Position(4, 4));
        List<String> moves = chessService.getPossibleMoves(queen);
        
        // Queen should be able to move in all directions until the edge of the board
        assertTrue(moves.contains("e4")); // right
        assertTrue(moves.contains("e5"));
        assertTrue(moves.contains("e6"));
        assertTrue(moves.contains("e7"));
        
        assertTrue(moves.contains("d4")); // left
        assertTrue(moves.contains("d5"));
        assertTrue(moves.contains("d6"));
        assertTrue(moves.contains("d7"));
        
        assertTrue(moves.contains("f4")); // up
        assertTrue(moves.contains("f5"));
        assertTrue(moves.contains("f6"));
        assertTrue(moves.contains("f7"));
        
        assertTrue(moves.contains("c4")); // down
        assertTrue(moves.contains("c5"));
        assertTrue(moves.contains("c6"));
        assertTrue(moves.contains("c7"));

        // Test queen at corner
        queen = new ChessPiece(PieceType.QUEEN, new Position(0, 0));
        moves = chessService.getPossibleMoves(queen);
        assertTrue(moves.contains("a2"));
        assertTrue(moves.contains("a3"));
        assertTrue(moves.contains("a4"));
        assertTrue(moves.contains("a5"));
        assertTrue(moves.contains("a6"));
        assertTrue(moves.contains("a7"));
        assertTrue(moves.contains("b1"));
        assertTrue(moves.contains("c1"));
        assertTrue(moves.contains("d1"));
        assertTrue(moves.contains("e1"));
        assertTrue(moves.contains("f1"));
        assertTrue(moves.contains("g1"));
        assertTrue(moves.contains("h1"));
    }
} 