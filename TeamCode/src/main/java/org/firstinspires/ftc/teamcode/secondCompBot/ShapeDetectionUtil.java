package org.firstinspires.ftc.teamcode.secondCompBot;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class for shape detection.
 *
 * @author mirza
 */
public final class ShapeDetectionUtil {
    // region Constructor
    private ShapeDetectionUtil() {
        // Empty by default
    }
    // endregion

    // region OpenCV
    /**
     * Used to process forwarded {@link Mat} image and return the result.
     *
     * @param filterd Image to process.
     * @return Returns processed image.
     */
    public static Mat processImage(final Mat filterd) {
        final Mat processed = new Mat(filterd.height(), filterd.width(), filterd.type());

        // Blur an image using a Gaussian filter
        Imgproc.GaussianBlur(filterd, processed, new Size(7, 7), 1);

//        // Switch from RGB to GRAY
//        Imgproc.cvtColor(processed, processed, Imgproc.COLOR_RGB2GRAY);

        // Find edges in an image using the Canny algorithm
        Imgproc.Canny(processed, processed, 200, 25);

        // Dilate an image by using a specific structuring element
        // https://en.wikipedia.org/wiki/Dilation_(morphology)
        Imgproc.dilate(processed, processed, new Mat(), new Point(-1, -1), 1);

        return processed;
    }

    /**
     * Used to mark outer rectangle and its corners.
     *
     * @param processedImage Image used for calculation of contours and corners.
     * @param originalImage Image on which marking is done.
     */
    public static void markOuterContour(final Mat processedImage,
                                        final Mat originalImage) {
        // Find contours of an image
        final List<MatOfPoint> allContours = new ArrayList<>();
        Imgproc.findContours(
                processedImage,
                allContours,
                new Mat(processedImage.size(), processedImage.type()),
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_NONE
        );

        // Filter out noise and display contour area value
        final List<MatOfPoint> filteredContours = allContours.stream()
                .filter(contour -> {
                    final double value = Imgproc.contourArea(contour);
                    final Rect rect = Imgproc.boundingRect(contour);

                    final boolean isNotNoise = value > 25000;

                    if (isNotNoise) {
                        Imgproc.putText (
                                originalImage,
                                "Area: " + (int) value,
                                new Point(rect.x + rect.width, rect.y + rect.height),
                                2,
                                0.5,
                                new Scalar(124, 252, 0),
                                1
                        );
                        Imgproc.putText (
                                originalImage,
                                "point:( " + (int) rect.x+", "+rect.y+")",
                                new Point(rect.x + rect.width, rect.y + rect.height+30),
                                2,
                                0.5,
                                new Scalar(124, 252, 0),
                                1
                        );

                        MatOfPoint2f dst = new MatOfPoint2f();
                        contour.convertTo(dst, CvType.CV_32F);
                        Imgproc.approxPolyDP(dst, dst, 0.02 * Imgproc.arcLength(dst, true), true);
                        Imgproc.putText (
                                originalImage,
                                "Points: " + dst.toArray().length,
                                new Point(rect.x + rect.width, rect.y + rect.height + 15),
                                2,
                                0.5,
                                new Scalar(124, 252, 0),
                                1
                        );
                    }

                    return isNotNoise;
                }).collect(Collectors.toList());

        // Mark contours
        Imgproc.drawContours(
                originalImage,
                filteredContours,
                -1, // Negative value indicates that we want to draw all of contours
                new Scalar(124, 252, 0), // Green color
                1
        );
    }
    public static List<MatOfPoint> getAllContours(final Mat processedImage){
        final List<MatOfPoint> allContours = new ArrayList<>();
        Imgproc.findContours(
                processedImage,
                allContours,
                new Mat(processedImage.size(), processedImage.type()),
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_NONE
        );
        final List<MatOfPoint> filteredContours = allContours.stream()
                .filter(contour -> {return Imgproc.contourArea(contour)>25000 && contour.toArray().length>=7 && contour.toArray().length<=9;}).collect(Collectors.toList());
        return filteredContours;
    }
}