package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.ShapeDetectionUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.BlueHueThresholdHigh;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.BlueHueThresholdLow;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.BlueSaturationThresholdHigh;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.BlueSaturationThresholdLow;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.BlueValueThresholdHigh;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.BlueValueThresholdLow;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.RedHueThresholdHigh;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.RedHueThresholdLow;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.RedSaturationThresholdHigh;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.RedSaturationThresholdLow;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.RedValueThresholdHigh;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.RedValueThresholdLow;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.camera_height;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.VisionConstants.camera_width;
@Config
public class VisionSubsystem extends SubsystemBase {
    OpenCvWebcam camera;
    Constants.GameConstants.StartingPosition color;
    Constants.GameConstants.GameType gameType;
    Telemetry telemetry;
    Rect center;
    Rect side;
    public static int centerBlueX =600;
    public static int centerBlueY =250;
    public static int centerBlueWidth = 500;
    public static int centerBlueHeight = 255;

    public static int centerRedX =260;
    public static int centerRedY =275;
    public static int centerRedWidth = 300;
    public static int centerRedHeight = 200;

    public static int leftX = 70;
    public static int leftY = 250;
    public static int leftWidth=350;
    public static int leftHeight=300;

    public static int rightX = 800;
    public static int rightY = 250;
    public static int rightWidth=350;
    public static int rightHeight=250;

    public static int areaThreshHold;
    public static int blueAreaThreshHold=3000;
    public static int redAreaThreshHold=3000;
    MatOfPoint largestCenterContour = new MatOfPoint();
    MatOfPoint largestSideContour = new MatOfPoint();
    List<MatOfPoint> allSideContours;
    List<MatOfPoint> allCenterContours;
    Mat processed;


    private class PipeLine extends OpenCvPipeline {

        private final Bitmap bitmap = Bitmap.createBitmap(camera_width, camera_height, Bitmap.Config.RGB_565);

        @Override
        public Mat processFrame(Mat input) {
            Mat centerFiltered = new Mat();
            Mat sideFiltered = new Mat();
            Mat centerMat =input.submat(center);
            Mat sideMat =input.submat(side);
            Mat filtered = new Mat();
            Mat processedSide;
            Mat processedCenter;
            switch (color){
                case RED:
                    filterRed(sideMat, sideFiltered);
                    filterRed(centerMat, centerFiltered);
                    filterRed(input,filtered);
                    processed = ShapeDetectionUtil.processImage(filtered);
                    processedSide = ShapeDetectionUtil.processImage(sideFiltered);
                    largestSideContour = new MatOfPoint();
                    allSideContours = ShapeDetectionUtil.getAllContours(processedSide);
                    for (MatOfPoint contour: allSideContours) {
                        if(largestSideContour.empty()){
                            largestSideContour =contour;
                        }
                        if( Imgproc.boundingRect(contour).area()> Imgproc.boundingRect(largestSideContour).area()){
                            largestSideContour =contour;
                        }
                    }
                    processedCenter = ShapeDetectionUtil.processImage(centerFiltered);
                    allCenterContours = ShapeDetectionUtil.getAllContours(processedCenter);
                    for (MatOfPoint contour: allCenterContours) {
                        if(largestCenterContour.empty()){
                            largestCenterContour =contour;
                        }
                        if( Imgproc.boundingRect(contour).area()> Imgproc.boundingRect(largestCenterContour).area()){
                            largestCenterContour =contour;
                        }
                    }
                    if(largestCenterContour.empty()&& largestSideContour.empty())gameType= Constants.GameConstants.GameType.LEFT;
                    else{
                        if(largestCenterContour.empty()){
                            if (Imgproc.boundingRect(largestSideContour).area()>areaThreshHold) gameType= Constants.GameConstants.GameType.RIGHT;
                            else gameType = Constants.GameConstants.GameType.LEFT;
                        }
                        else if(largestSideContour.empty()){
                            if (Imgproc.boundingRect(largestCenterContour).area()>areaThreshHold) gameType= Constants.GameConstants.GameType.CENTER;
                            else gameType = Constants.GameConstants.GameType.LEFT;
                        }else{
                            if(Imgproc.boundingRect(largestCenterContour).area()>Imgproc.boundingRect(largestSideContour).area()){
                                if(Imgproc.boundingRect(largestCenterContour).area()>areaThreshHold)gameType= Constants.GameConstants.GameType.CENTER;
                                else gameType=Constants.GameConstants.GameType.LEFT;
                            }
                            else {
                                if(Imgproc.boundingRect(largestSideContour).area()>areaThreshHold)gameType= Constants.GameConstants.GameType.RIGHT;
                                else gameType=Constants.GameConstants.GameType.LEFT;
                            }
                        }
                    }
                    break;
                case BLUE:
                    filterBlue(sideMat,sideFiltered);
                    filterBlue(centerMat,centerFiltered);
                    filterBlue(input,filtered);
                    processed = ShapeDetectionUtil.processImage(filtered);
                    processedSide = ShapeDetectionUtil.processImage(sideFiltered);
                    largestSideContour = new MatOfPoint();
                    allSideContours = ShapeDetectionUtil.getAllContours(processedSide);
                    for (MatOfPoint contour: allSideContours) {
                        if(largestSideContour.empty()){
                            largestSideContour =contour;
                        }
                        if( Imgproc.boundingRect(contour).area()> Imgproc.boundingRect(largestSideContour).area()){
                            largestSideContour =contour;
                        }
                    }
                    processedCenter = ShapeDetectionUtil.processImage(centerFiltered);
                    allCenterContours = ShapeDetectionUtil.getAllContours(processedCenter);
                    for (MatOfPoint contour: allCenterContours) {
                        if(largestCenterContour.empty()){
                            largestCenterContour =contour;
                        }
                        if( Imgproc.boundingRect(contour).area()> Imgproc.boundingRect(largestCenterContour).area()){
                            largestCenterContour =contour;
                        }
                    }
                    if(largestCenterContour.empty()&& largestSideContour.empty())gameType= Constants.GameConstants.GameType.RIGHT;
                    else{
                        if(largestCenterContour.empty()){
                            if (Imgproc.boundingRect(largestSideContour).area()>areaThreshHold) gameType= Constants.GameConstants.GameType.LEFT;
                            else gameType = Constants.GameConstants.GameType.RIGHT;
                        }
                        else if(largestSideContour.empty()){
                            if (Imgproc.boundingRect(largestCenterContour).area()>areaThreshHold) gameType= Constants.GameConstants.GameType.CENTER;
                            else gameType = Constants.GameConstants.GameType.RIGHT;
                        }else{
                            if(Imgproc.boundingRect(largestCenterContour).area()>Imgproc.boundingRect(largestSideContour).area()){
                                if(Imgproc.boundingRect(largestCenterContour).area()>areaThreshHold)gameType= Constants.GameConstants.GameType.CENTER;
                                else gameType=Constants.GameConstants.GameType.RIGHT;
                            }
                            else {
                                if(Imgproc.boundingRect(largestSideContour).area()>areaThreshHold)gameType= Constants.GameConstants.GameType.LEFT;
                                else gameType=Constants.GameConstants.GameType.RIGHT;
                            }
                        }
                    }
            }


//            ShapeDetectionUtil.markOuterContour(processed,input);
             centerFiltered.release();
             sideFiltered.release();
             //processed.release();
             telemetry.addData("game type",gameType);
            try {
                telemetry.addData("largest center area",Imgproc.boundingRect(largestCenterContour).area());
                telemetry.addData("largest left area", Imgproc.boundingRect(largestSideContour).area());
                telemetry.addData("largest center size",largestCenterContour.toArray().length);
                telemetry.addData("largest left size", largestSideContour.toArray().length);
            }catch (Exception e){
                telemetry.addData("faild","faild");
            }

             telemetry.update();
            for (MatOfPoint contour: allCenterContours) {
                contour.release();
            }
            for (MatOfPoint contour: allSideContours) {
                contour.release();
            }
            Imgproc.rectangle(processed, center, new Scalar(255, 0, 0), 3);
            Imgproc.rectangle(processed, side, new Scalar(0, 0, 255), 3);

            return processed;
        }



//            @Override
//    public Mat processFrame(Mat input) {
//        center = new Rect(centerRedX, centerRedY, centerRedWidth, centerRedHeight);
//        side = new Rect(rightX,rightY,rightWidth,rightHeight);
//
//        Imgproc.rectangle(input, center, new Scalar(255, 0, 0), 3);
//        Imgproc.rectangle(input, side, new Scalar(0, 0, 255), 3);
//
//        return input;
//    }


        private void filterRed(Mat input,
                               Mat out) {
            Imgproc.cvtColor(input, out, Imgproc.COLOR_RGB2HSV);
            Core.inRange(out, new Scalar(RedHueThresholdLow, RedSaturationThresholdLow, RedValueThresholdLow),
                    new Scalar(RedHueThresholdHigh, RedSaturationThresholdHigh, RedValueThresholdHigh), out);
        }


        private void filterBlue(Mat input,
                                Mat out) {
            Imgproc.cvtColor(input, out, Imgproc.COLOR_RGB2HSV);
            Core.inRange(out, new Scalar(BlueHueThresholdLow, BlueSaturationThresholdLow, BlueValueThresholdLow),
                    new Scalar(BlueHueThresholdHigh, BlueSaturationThresholdHigh, BlueValueThresholdHigh), out);
        }
    }



    public VisionSubsystem(HardwareMap hardwareMap, Telemetry telemetry,    Constants.GameConstants.StartingPosition color) {
        this.color = color;
        switch (color){
            case RED:
                center = new Rect(centerRedX, centerRedY, centerRedWidth, centerRedHeight);
                side = new Rect(rightX,rightY,rightWidth,rightHeight);
                areaThreshHold = redAreaThreshHold;
                break;
            case BLUE:
                center = new Rect(centerBlueX, centerBlueY, centerBlueWidth, centerBlueHeight);
                side = new Rect(leftX,leftY,leftWidth,leftHeight);
                areaThreshHold=blueAreaThreshHold;
                break;
        }

        this.telemetry = telemetry;
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(camera_width, camera_height, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(new PipeLine());
                FtcDashboard.getInstance().startCameraStream(camera,0);
            }
            @Override
            public void onError(int errorCode) {}
        });
    }

    public void stop(){
        camera.closeCameraDevice();
    }
    public Constants.GameConstants.GameType getGameType(){
        return gameType;
    }
}

