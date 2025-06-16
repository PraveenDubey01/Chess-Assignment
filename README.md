# Chess Movement Simulator

### Description
A simple Java console application that simulates possible movements of Pawn, King, and Queen on a standard 8x8 chessboard.

### Technologies
- Java 17+
- No frameworks required
- Maven/Gradle for build (optional)

### How to Run
1. Compile and run the `ChessApplication.java`
2. Input example: `King, D5`
3. Output: `C4, C5, C6, D4, D6, E4, E5, E6`

### Assumptions
- Pawn moves only 1 step forward (upward from White’s perspective).
- Input is case-insensitive for piece type and position.

### Unit Testing
You can add unit tests for `ChessService` and `Position` class using JUnit 5.
