package org.firstinspires.ftc.teamcode.AMDA;

import java.util.ArrayList;

public class Location implements Comparable<Location> {
    int x, y, score, color;
    ArrayList<Location> moves;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Location other) {
        return this.x == other.x && this.y == other.y;
    }

    public String toString() {
        return "Location x: " + x + " y: " + y;
    }

    @Override
    public int compareTo(Location other) {
        int returnVal = this.score - other.score;
        if (returnVal == 0) {
            for (int i = 0; i < moves.size(); i++) {
                if (i == moves.size() - 1) {
                    return 0;
                }
                int immediateScore = moves.get(i).score - other.moves.get(i).score;
                if (immediateScore != 0) {
                    return immediateScore;
                }
            }
        } else {
            return returnVal;
        }

        return 0;
    }
}
