package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.opModes.BlueBoardAuto;
import org.firstinspires.ftc.teamcode.firstCompBot.opModes.RedBoardAuto;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.BlueHueThresholdHigh;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.BlueHueThresholdLow;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.BlueSaturationThresholdHigh;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.BlueSaturationThresholdLow;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.BlueValueThresholdHigh;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.BlueValueThresholdLow;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.RedHueThresholdHigh;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.RedHueThresholdLow;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.RedSaturationThresholdHigh;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.RedSaturationThresholdLow;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.RedValueThresholdHigh;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.RedValueThresholdLow;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.camera_height;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.VisionConstants.camera_width;

public class VisionSubsystem extends SubsystemBase {
    OpenCvWebcam camera;
    Constants.GameConstants.StartingPosition color;
    Constants.GameConstants.GameType gameType;
    RedBoardAuto redBoardAuto;
    BlueBoardAuto blueBoardAuto;
    Telemetry telemetry;
    //Rect left;
    Rect center;
    Rect right;


    private class PipeLine extends OpenCvPipeline {

        private final Bitmap bitmap = Bitmap.createBitmap(camera_width, camera_height, Bitmap.Config.RGB_565);

        @Override
        public Mat processFrame(Mat input) {

//            Mat leftMat = input.submat(left);
            Mat centerMat = input.submat(center);
            Mat rightMat = input.submat(right);
//            Mat leftFiltered = new Mat();
            Mat centerFiltered = new Mat();
            Mat rightFiltered = new Mat();

            switch (color) {
                case RED:
//                    filterRed(leftMat, leftFiltered);
                    filterRed(centerMat, centerFiltered);
                    filterRed(rightMat, rightFiltered);
                    break;
                case BLUE:
//                    filterBlue(leftMat, leftFiltered);
                    filterBlue(centerMat, centerFiltered);
                    filterBlue(rightMat, rightFiltered);
            }

//            int lcr[] = {Core.countNonZero(leftFiltered),Core.countNonZero(centerFiltered),Core.countNonZero(rightFiltered)};
            int cr[] = {Core.countNonZero(centerFiltered), Core.countNonZero(rightFiltered)};

            switch (color){
                case RED:
                    if (cr[0] > cr[1]) {
                        redBoardAuto.gameType = Constants.GameConstants.GameType.CENTER;
                        gameType = Constants.GameConstants.GameType.CENTER;
                    } else if (cr[1] > cr[0]) {
                        redBoardAuto.gameType = Constants.GameConstants.GameType.RIGHT;
                        gameType = Constants.GameConstants.GameType.RIGHT;
                    } else {
                        redBoardAuto.gameType = Constants.GameConstants.GameType.CENTER;
                        gameType = Constants.GameConstants.GameType.CENTER;
                    }
                    if (color == Constants.GameConstants.StartingPosition.RED && cr[0] < 20000 && cr[1] < 20000) {
                        redBoardAuto.gameType = Constants.GameConstants.GameType.LEFT;
                        gameType = Constants.GameConstants.GameType.LEFT;
                    }
                    break;
                case BLUE:
                    if (cr[0] > cr[1]) {
                        blueBoardAuto.gameType = Constants.GameConstants.GameType.CENTER;
                        gameType = Constants.GameConstants.GameType.CENTER;
                    } else if (cr[1] > cr[0]) {
                        blueBoardAuto.gameType = Constants.GameConstants.GameType.RIGHT;
                        gameType = Constants.GameConstants.GameType.RIGHT;
                    } else {
                        blueBoardAuto.gameType = Constants.GameConstants.GameType.CENTER;
                        gameType = Constants.GameConstants.GameType.CENTER;
                    }
                    if (cr[0] < 20000 && cr[1] < 20000) {
                        blueBoardAuto.gameType = Constants.GameConstants.GameType.LEFT;
                        gameType = Constants.GameConstants.GameType.LEFT;
                    }
                    break;
            }

            //Imgproc.rectangle(input, left, new Scalar(0, 255, 0), 3);
            Imgproc.rectangle(input, center, new Scalar(255, 0, 0), 3);
            Imgproc.rectangle(input, right, new Scalar(0, 0, 255), 3);

//            leftMat.release();
            centerMat.release();
            rightMat.release();
//            leftFiltered.release();
            centerFiltered.release();
            rightFiltered.release();
            telemetry.addData("vision sees center,right:", " %s, %s", cr[0], cr[1]);
            telemetry.addData("game type", gameType);
            telemetry.update();
            switch (color) {
                case RED:
                        redBoardAuto.initAuto(telemetry, gameType);
                    break;
                case BLUE:
                        blueBoardAuto.initAuto(telemetry, gameType);

            }
            return input;
        }


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



    public VisionSubsystem(HardwareMap hardwareMap, Telemetry telemetry, RedBoardAuto redBoardAuto) {
        this.redBoardAuto = redBoardAuto;
//        left = new Rect(camera_width/3,0,camera_width/3,camera_height);
        center = new Rect(camera_width/3,0,camera_width/3 ,camera_height);
        right = new Rect((camera_width/3)*2,0,camera_width/3,camera_height);
        this.color = Constants.GameConstants.StartingPosition.RED;
        this.telemetry = telemetry;
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"));
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


//        Rect vision;
//
//        switch (RobotUniversal.startingPosition) {
//            default:
//            case Red:
//                vision = Red;
//                break;
//            case Blue:
//                vision = Blue;
//                break;
//        }
//
//        this.Vision = vision;
    }
    public VisionSubsystem(HardwareMap hardwareMap, Telemetry telemetry, BlueBoardAuto blueBoardAuto) {
        this.blueBoardAuto = blueBoardAuto;
        this.color = Constants.GameConstants.StartingPosition.BLUE;
//        left = new Rect(camera_width/3,0,camera_width/3,camera_height);
        center = new Rect(camera_width/3,0,camera_width/3 ,camera_height);
        right = new Rect((camera_width/3)*2,0,camera_width/3,camera_height);
        this.telemetry = telemetry;
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"));
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


//        Rect vision;
//
//        switch (RobotUniversal.startingPosition) {
//            default:
//            case Red:
//                vision = Red;
//                break;
//            case Blue:
//                vision = Blue;
//                break;
//        }
//
//        this.Vision = vision;
    }
    public void stop(){
        camera.closeCameraDevice();
    }
}

