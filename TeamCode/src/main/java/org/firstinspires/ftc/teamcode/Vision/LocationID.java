package org.firstinspires.ftc.teamcode.Vision;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocationID implements VisionProcessor {


    public enum ElementPosition {
        LEFT,
        MIDDLE,
        RIGHT
    }


    public static  Point[] Region1 = {new Point(0, 200), new Point(100, 300)};
    public static  Point[] Region2 = {new Point(320, 200), new Point(420, 300)};
    public static  Point[] Region3 = {new Point(640, 200), new Point(740, 300)};
    static final Scalar BLUE = new Scalar(0, 0, 255);
    CopyOnWriteArrayList<Mat> RegionCr = new CopyOnWriteArrayList<>();
    Mat YCrCb = new Mat();
    Mat Cr = new Mat();
    CopyOnWriteArrayList<Integer> averages = new CopyOnWriteArrayList<>();

    // Volatile since accessed by OpMode thread without synchronization
    private volatile ElementPosition position = ElementPosition.LEFT;

    void inputToCr(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cr, 1);
    }




    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        //Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2HSV);
       // Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2YCrCb);
      //  RegionCr.add(Cr.submat(new Rect(Region1[0], Region1[1])));
        //RegionCr.add(Cr.submat(new Rect(Region2[0], Region2[1])));
        //RegionCr.add(Cr.submat(new Rect(Region3[0], Region3[1])));


        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint selectedPaint = new Paint();
        selectedPaint.setColor(Color.RED);
        selectedPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new android.graphics.Rect((int)Region1[0].x, (int)Region1[0].y, (int)Region1[1].x, (int)Region1[1].y), selectedPaint);
        canvas.drawRect(new android.graphics.Rect((int)Region2[0].x, (int)Region2[0].y, (int)Region2[1].x, (int)Region2[1].y), selectedPaint);
        canvas.drawRect(new android.graphics.Rect((int)Region3[0].x, (int)Region3[0].y, (int)Region3[1].x, (int)Region3[1].y), selectedPaint);

    }

    /*

    /*
     * Colour constants
     */


    /**
     * Takes the input and converts it into YCrCb colour space, then extracts the Cr channel to the 'Cr' matrix
     * @param input The input frame
     */

    /**
     * Initialises the pipeline and adds all of the submats to the RegionCr ArrayList
     * @param firstFrame First captured frame
     */
    public void init(Mat firstFrame)
    {
        inputToCr(firstFrame);

    }

    /**
     * Processes the frame and determines where the TSE is
     * @param input Frame from the camera that is used to determine where the TSE is
     * @return The input image with annotated rectangles to show on the camera preview screen
     */
    public Mat processFrame(Mat input)
    {
        inputToCr(input);

        averages.clear();

        /*
         * Computes the average pixel value of each submat region and adds them to the
         * average ArrayList
         */
        averages.add((int) Core.mean(RegionCr.get(0)).val[0]);
        averages.add((int) Core.mean(RegionCr.get(1)).val[0]);
        averages.add((int) Core.mean(RegionCr.get(2)).val[0]);

        /*
         * Draws rectangles on the screen denoting the locations of the sample regions; used for visual
         * aid
         */
        Imgproc.rectangle(
                input, // Buffer to draw on
                Region1[0], // First point which defines the rectangle
                Region1[1], // Second point which defines the rectangle
                BLUE, // The colour the rectangle is drawn in
                2); // Thickness of the rectangle lines
        Imgproc.rectangle(
                input, // Buffer to draw on
                Region2[0], // First point which defines the rectangle
                Region2[1], // Second point which defines the rectangle
                BLUE, // The colour the rectangle is drawn in
                2); // Thickness of the rectangle lines
        Imgproc.rectangle(
                input, // Buffer to draw on
                Region3[0], // First point which defines the rectangle
                Region3[1], // Second point which defines the rectangle
                BLUE, // The colour the rectangle is drawn in
                2); // Thickness of the rectangle lines

        /*
         * Determines the region with the maximum pixel value by finding the maximum value in the averages
         * ArrayList
         */
        int maxRegion = averages.indexOf(Collections.min(averages));

        /*
         * Based on the region that has the minimum Cr value, the position of the TSE is determined
         * accordingly
         *
         * e.g. If the first region (region at index 0) has the minimum Cr value, then the position
         * of the TSE is the left
         */
        switch (maxRegion) {
            case 0: default:
                position = ElementPosition.LEFT;
                break;
            case 1:
                position = ElementPosition.MIDDLE;
                break;
            case 2:
                position = ElementPosition.RIGHT;
                break;
        }

        /*
         * Returns the input buffer to the viewport with the annotations above added
         */
        return input;
    }

    /**
     * Returns the averages of each of the three regions in an ArrayList
     * @return The averages
     */
    public CopyOnWriteArrayList<Integer> getAnalysis()
    {
        return averages;
    }

    /**
     * Returns the average of the specified region
     * @param index Specified region
     * @return The average
     */
    public Integer getAnalysis(int index) {
        return averages.get(index);
    }

    /**
     * Returns the position of the TSE
     * @return The position of the TSE
     */
    public ElementPosition getPosition() {
        return position;
    }
}

