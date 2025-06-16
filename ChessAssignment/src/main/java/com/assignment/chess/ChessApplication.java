package com.assignment.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.chess.model.ChessPiece;
import com.assignment.chess.model.PieceType;
import com.assignment.chess.model.Position;
import com.assignment.chess.service.ChessService;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ChessApplication {

    private static final Logger logger = LoggerFactory.getLogger(ChessApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting Chess Application");
        SpringApplication.run(ChessApplication.class, args);
        
        try (Scanner scanner = new Scanner(System.in)) {
            logger.info("Chess Application initialized successfully");
            logger.info("Enter piece type and position (e.g. King, D5):");
            String input = scanner.nextLine();
            logger.debug("Received input: {}", input);

            String[] parts = input.split(",");
            if (parts.length != 2) {
                logger.error("Invalid input format. Expected format: 'PieceType, Position'");
                return;
            }

            String pieceTypeStr = parts[0].trim();
            String positionStr = parts[1].trim();
            logger.debug("Parsed input - Piece Type: {}, Position: {}", pieceTypeStr, positionStr);

            PieceType pieceType = PieceType.fromString(pieceTypeStr);
            Position startPosition = Position.fromChessNotation(positionStr);

            if (pieceType == null || startPosition == null) {
                logger.error("Invalid piece type '{}' or position '{}'", pieceTypeStr, positionStr);
                return;
            }

            logger.info("Processing moves for {} at position {}", pieceType, startPosition);
            ChessPiece chessPiece = new ChessPiece(pieceType, startPosition);
            ChessService service = new ChessService();
            List<String> moves = service.getPossibleMoves(chessPiece);

            if (!moves.isEmpty()) {
                logger.info("Found {} possible moves for {} at {}", moves.size(), pieceType, startPosition.toString());
                logger.debug("Possible moves: {}", String.join(", ", moves));
            } else {
                logger.info("No possible moves available for {} at {}", pieceType, startPosition);
            }
        } catch (Exception e) {
            logger.error("An error occurred while processing chess moves", e);
        }
        
        logger.info("Chess Application shutting down");
    }
}
