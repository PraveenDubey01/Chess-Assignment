package com.assignment.chess.service;

import java.util.ArrayList;
import java.util.List;
import com.assignment.chess.model.Position;
import com.assignment.chess.model.ChessPiece;
import com.assignment.chess.utils.BoardUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessService {
    private static final Logger logger = LoggerFactory.getLogger(ChessService.class);

    public List<String> getPossibleMoves(ChessPiece piece) {
        logger.debug("Calculating possible moves for {} at position {}", piece.getType(), piece.getPosition().toChessNotation());
        List<String> validMoves = new ArrayList<>();
        Position pos = piece.getPosition();
        switch (piece.getType()) {
            case PAWN -> {
                logger.debug("Processing PAWN movement");
                int newRow = pos.getRow() - 1;
                if (BoardUtils.isValidCoordinate(newRow, pos.getColumn())) {
                    String move = new Position(newRow, pos.getColumn()).toChessNotation();
                    validMoves.add(move);
                    logger.debug("Added valid move for PAWN: {}", move);
                }
            }
            case KING -> {
                logger.debug("Processing KING movement");
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        if (dr == 0 && dc == 0) continue;
                        int newRow = pos.getRow() + dr;
                        int newCol = pos.getColumn() + dc;
                        if (BoardUtils.isValidCoordinate(newRow, newCol)) {
                            String move = new Position(newRow, newCol).toChessNotation();
                            validMoves.add(move);
                            logger.debug("Added valid move for KING: {}", move);
                        }
                    }
                }
            }
            case QUEEN -> {
                logger.debug("Processing QUEEN movement");
                int[][] directions = {
                    {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                    {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
                };
                for (int[] dir : directions) {
                    int row = pos.getRow();
                    int col = pos.getColumn();
                    while (true) {
                        row += dir[0];
                        col += dir[1];
                        if (BoardUtils.isValidCoordinate(row, col)) {
                            String move = new Position(row, col).toChessNotation();
                            validMoves.add(move);
                            logger.debug("Added valid move for QUEEN: {}", move);
                        } else break;
                    }
                }
            }
        }
        logger.info("Found {} valid moves for {} at {}", validMoves.size(), piece.getType(), pos.toChessNotation());
        return validMoves;
    }
}