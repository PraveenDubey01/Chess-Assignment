package com.assignment.chess;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.assignment.chess.model.ChessPiece;
import com.assignment.chess.model.PieceType;
import com.assignment.chess.model.Position;
import com.assignment.chess.service.ChessService;
import com.assignment.chess.utils.BoardUtils;

import java.util.List;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testPositionCreationAndConversion() {
		// Test valid position creation
		Position pos = new Position(0, 0);
		assertEquals(0, pos.getRow());
		assertEquals(0, pos.getColumn());
		assertEquals("A8", pos.toChessNotation());

		// Test position from chess notation
		Position fromNotation = Position.fromChessNotation("E4");
		assertNotNull(fromNotation);
		assertEquals(4, fromNotation.getRow());
		assertEquals(4, fromNotation.getColumn());

		// Test invalid chess notation
		assertNull(Position.fromChessNotation("X9"));
		assertNull(Position.fromChessNotation("A"));
		assertNull(Position.fromChessNotation(""));
	}

	@Test
	void testChessPiece() {
		Position pos = new Position(0, 0);
		ChessPiece piece = new ChessPiece(PieceType.KING, pos);
		
		assertEquals(PieceType.KING, piece.getType());
		assertEquals(pos, piece.getPosition());
	}

	@Test
	void testPieceType() {
		// Test valid piece types
		assertEquals(PieceType.PAWN, PieceType.fromString("PAWN"));
		assertEquals(PieceType.KING, PieceType.fromString("KING"));
		assertEquals(PieceType.QUEEN, PieceType.fromString("QUEEN"));

		// Test case insensitivity
		assertEquals(PieceType.PAWN, PieceType.fromString("pawn"));
		
		// Test invalid piece types
		assertNull(PieceType.fromString("INVALID"));
		assertNull(PieceType.fromString(""));
	}

	@Test
	void testBoardUtils() {
		// Test valid coordinates
		assertTrue(BoardUtils.isValidCoordinate(0, 0));
		assertTrue(BoardUtils.isValidCoordinate(7, 7));
		assertTrue(BoardUtils.isValidCoordinate(4, 4));

		// Test invalid coordinates
		assertFalse(BoardUtils.isValidCoordinate(-1, 0));
		assertFalse(BoardUtils.isValidCoordinate(0, -1));
		assertFalse(BoardUtils.isValidCoordinate(8, 0));
		assertFalse(BoardUtils.isValidCoordinate(0, 8));
	}

	@Test
	void testChessServicePawnMoves() {
		ChessService service = new ChessService();
		ChessPiece pawn = new ChessPiece(PieceType.PAWN, new Position(6, 0));
		
		List<String> moves = service.getPossibleMoves(pawn);
		assertFalse(moves.isEmpty());
		assertEquals(1, moves.size());
		assertEquals("A2", moves.get(0));
	}

	@Test
	void testChessServiceKingMoves() {
		ChessService service = new ChessService();
		ChessPiece king = new ChessPiece(PieceType.KING, new Position(4, 4));
		
		List<String> moves = service.getPossibleMoves(king);
		assertFalse(moves.isEmpty());
		assertEquals(8, moves.size());
		assertTrue(moves.contains("E4"));
		assertTrue(moves.contains("E6"));
		assertTrue(moves.contains("D5"));
		assertTrue(moves.contains("F5"));
	}

	@Test
	void testChessServiceQueenMoves() {
		ChessService service = new ChessService();
		ChessPiece queen = new ChessPiece(PieceType.QUEEN, new Position(4, 4));
		
		List<String> moves = service.getPossibleMoves(queen);
		assertFalse(moves.isEmpty());
		assertTrue(moves.contains("E4"));
		assertTrue(moves.contains("E8"));
		assertTrue(moves.contains("A4"));
		assertTrue(moves.contains("H4"));
		assertTrue(moves.contains("A8"));
		assertTrue(moves.contains("H1"));
	}

	@Test
	void testEdgeCases() {
		ChessService service = new ChessService();
		
		// Test piece at corner
		ChessPiece cornerPiece = new ChessPiece(PieceType.KING, new Position(0, 0));
		List<String> cornerMoves = service.getPossibleMoves(cornerPiece);
		assertEquals(3, cornerMoves.size());
		
		// Test piece at edge
		ChessPiece edgePiece = new ChessPiece(PieceType.QUEEN, new Position(0, 4));
		List<String> edgeMoves = service.getPossibleMoves(edgePiece);
		assertTrue(edgeMoves.size() > 0);
	}
}
