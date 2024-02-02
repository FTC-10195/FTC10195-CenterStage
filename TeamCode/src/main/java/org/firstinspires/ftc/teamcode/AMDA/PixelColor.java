package org.firstinspires.ftc.teamcode.AMDA;

public class PixelColor {
    public enum Color {
        WHITE,
        YELLOW,
        PURPLE,
        GREEN
    }

    Color pixelColor;

    public PixelColor(int i) {
        switch (i) {
            case 1:
                pixelColor = Color.WHITE;
                break;
            case 2:
                pixelColor = Color.YELLOW;
                break;
            case 3:
                pixelColor = Color.PURPLE;
                break;
            case 4:
                pixelColor = Color.GREEN;
                break;
        }
    }

    public Color getPixelColor() {
        return pixelColor;
    }
}
