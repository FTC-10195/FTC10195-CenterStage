package org.firstinspires.ftc.teamcode.AMDA;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp
public class Calculator extends LinearOpMode {
    static ArrayList<Location> possibleMoves = new ArrayList<>();
    Telemetry m_telemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        m_telemetry = telemetry;

        int[][] board = new int[11][13];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i % 2 == j % 2) {
                    board[i][j] = 9;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        waitForStart();

        long before = System.nanoTime();
        printBoard(board);
        telemetry.addLine("Current Score: " + calculateScore(board));
        telemetry.addLine("Number of mosaics: " + checkMosaics(board));
        telemetry.addLine();

        // find possible places to put
        ArrayList<Location> places = findPossibleMoves(board);

        int[][] boardCpy = board.clone();
        int depth = 3;

        ArrayList<Location> currentMoves = new ArrayList<>();

        for (Location l : places) {
            recursiveSearch(boardCpy, l, depth, currentMoves);
        }

        printMoves(possibleMoves);

        long after = System.nanoTime();

        telemetry.addLine("\nThis took " + (after - before) / 1.0E9 + " seconds to run.");
    }

    static ArrayList<Location> findPossibleMoves(int[][] board) {
        ArrayList<Location> places = new ArrayList<>();
        for (int j = 0; j < board[0].length; j++) {
            int i = 0;
            if (j % 2 == 0) {
                i = 1;
            }
            try {
                while (board[i][j] != 0) {
                    i += 2;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
            boolean left = true, right = true;

            if (i == 0) {
                places.add(new Location(i, j));
                continue;
            } else {
                if (j > 0) {
                    left = board[i - 1][j - 1] != 0;
                }
                if (j < board[0].length - 1) {
                    right = board[i - 1][j + 1] != 0;
                }

                if (left && right) {
                    places.add(new Location(i, j));
                    continue;
                }
            }
        }

        return places;
    }

    public void recursiveSearch(int[][] board, Location l, int depth, ArrayList<Location> currentMoves) {
        if (depth == 0) {
            // Reached the desired depth, evaluate the position and add it to possibleMoves
            Location lc = new Location(l.x, l.y);
            lc.score = calculateScore(board);
            lc.color = board[l.x][l.y];
            lc.moves = currentMoves;
            possibleMoves.add(lc);
            return;
        }

        int[][] boardCopy = board.clone();
        ArrayList<Location> places = findPossibleMoves(boardCopy);

        for (Location loc : places) {
            for (int i = 1; i <= 4; i++) {
                // Make a move
                boardCopy[loc.x][loc.y] = i;

                ArrayList<Location> currentMovesCpy = (ArrayList<Location>) currentMoves.clone();

                Location lc = new Location(loc.x, loc.y);
                lc.color = i;
                lc.score = calculateScore(boardCopy);

                currentMovesCpy.add(lc);

                // Call the recursive function for the next depth
                recursiveSearch(boardCopy, loc, depth - 1, currentMovesCpy);

                // Undo the move
                boardCopy[loc.x][loc.y] = 0;
            }
        }
    }

    public void printBoard(int[][] board) {
        telemetry.addLine();
        Map<Integer, String> colorMap = new HashMap<>();
        colorMap.put(0, "\u001B[30m"); // none
        colorMap.put(2, "\u001B[33m"); // yellow
        colorMap.put(3, "\u001B[32m"); // green
        colorMap.put(4, "\u001B[35m"); // purple
        for (int i = board.length - 1; i >= 0; i--) {
            telemetry.addData("\u001B[31m" + "%-2d " + "\u001B[0m", i);
            for (int j = 0; j < board[0].length; j++) {
                telemetry
                        .addData("", colorMap.getOrDefault(board[i][j], "") + (board[i][j] == 9 ? " " : board[i][j]) + "\u001B[0m ");
            }
            telemetry.addLine();
        }
        telemetry.addData("", "   ");
        for (int j = 0; j < board[0].length; j++) {
            telemetry.addData("", "\u001B[31m" + (char) (j + 'a') + "\u001B[0m" + " ");
        }
        telemetry.addLine();
        telemetry.addLine();
    }

    public void printMoves(ArrayList<Location> moves) {
        moves.sort(Collections.reverseOrder());

        telemetry.addLine("Best placement: ");
        Location idealLocation = moves.get(0);
        telemetry.addLine(
                "Place first pixel at " +
                        (char) (idealLocation.moves.get(0).y + 'a') + " " +
                        idealLocation.moves.get(0).x + " with color " +
                        Color.values()[idealLocation.moves.get(0).color - 1] +
                        ", resulting in a score of " +
                        idealLocation.moves.get(0).score);
        telemetry.addLine(
                "Place second pixel at " +
                        (char) (idealLocation.moves.get(1).y + 'a') + " " +
                        idealLocation.moves.get(1).x + " with color " +
                        Color.values()[idealLocation.moves.get(1).color - 1] +
                        ", resulting in a score of " +
                        idealLocation.moves.get(1).score);
        telemetry.addLine("This will eventually get you a score of " + idealLocation.score + " in the future.");

        if (moves.size() == 0) {
            telemetry.addLine("NO PLACES LEFT");
        }

        telemetry.addLine();
    }

    public int calculateScore(int[][] board) {
        int score = 0;
        int highest = -1;
        for (int i = 0; i < board.length; i++) {
            int offset = 0;
            if (i % 2 == 0) {
                offset = 1;
            }
            for (int j = 0; j < board[0].length - offset; j += 2) {
                if (board[i][j + offset] > 0) {
                    if (i > highest) {
                        highest = i;
                    }
                    score += 3;
                }
            }
        }

        if (highest >= 2) {
            score += 10;
        }
        if (highest >= 5) {
            score += 10;
        }
        if (highest >= 8) {
            score += 10;
        }

        score += checkMosaics(board) * 10;

        return score;
    }

    public int checkMosaics(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = (i % 2 == 0 ? 1 : 0); j < cols; j += 2) {
                if (isColored(board[i][j])) {
                    // We found a colored pixel, so try to form a mosaic triangle
                    if (isMosaic(board, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count / 3;
    }

    private boolean isMosaic(int[][] board, int x, int y) {
        // Possible directions for the other two vertices of the triangle
        int[][] directions = { { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { 0, 2 }, { 0, -2 } };

        for (int[] dir1 : directions) {
            int x1 = x + dir1[0];
            int y1 = y + dir1[1];

            if (isValid(x1, y1, board) && isColored(board[x1][y1])) {
                for (int[] dir2 : directions) {
                    int x2 = x1 + dir2[0];
                    int y2 = y1 + dir2[1];

                    if (isValid(x2, y2, board) && isColored(board[x2][y2])) {
                        if (isValidTriangle(x, y, x1, y1, x2, y2, board)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean isColored(int c) {
        return c >= 2 && c <= 4;
    }

    private boolean isValid(int x, int y, int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] != 9;
    }

    private boolean isValidTriangle(int x0, int y0, int x1, int y1, int x2, int y2, int[][] grid) {
        boolean pointsDistinct = (x0 != x1 || y0 != y1) && (x0 != x2 || y0 != y2) && (x1 != x2 || y1 != y2);
        boolean isTriangle = pointsDistinct
                && numColoredNeighbors(x0, y0, grid) == 2
                && numColoredNeighbors(x1, y1, grid) == 2
                && numColoredNeighbors(x2, y2, grid) == 2;

        // Check if the colors are correct
        int first = grid[x0][y0];
        int second = grid[x1][y1];
        int third = grid[x2][y2];
        boolean correctColors = ((first == second) && (second == third))
                || ((first != second) && (second != third) && (first != third));
        return isTriangle && correctColors;
    }

    private int numColoredNeighbors(int x, int y, int[][] board) {
        int[][] directions = { { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { 0, 2 }, { 0, -2 } };
        int count = 0;

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValid(newX, newY, board) && isColored(board[newX][newY])) {
                count++; // Found a neighboring colored pixel
            }
        }

        return count;
    }
}
