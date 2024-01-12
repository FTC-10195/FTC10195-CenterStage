package org.firstinspires.ftc.teamcode.AMDA;

import org.firstinspires.ftc.teamcode.AMDA.PixelColor.Color;

public class Pixel {
    PixelColor color;

    public Pixel (int col) {
        color = new PixelColor(col);
    }

    public Color getColor() {
        return color.getPixelColor();
    }

}
