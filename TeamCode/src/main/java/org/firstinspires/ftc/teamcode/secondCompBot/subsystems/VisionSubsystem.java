package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
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

public class VisionSubsystem extends SubsystemBase {
    OpenCvWebcam camera;
    Constants.GameConstants.StartingPosition color;
    Constants.GameConstants.GameType gameType;
    Telemetry telemetry;
    Rect center;
    Rect right;


    private class PipeLine extends OpenCvPipeline {

        private final Bitmap bitmap = Bitmap.createBitmap(camera_width, camera_height, Bitmap.Config.RGB_565);

        @Override
        public Mat processFrame(Mat input) {
            Mat filterd = new Mat();
            switch (color){
                case RED:
                    filterRed(input, filterd);
                    break;
                case BLUE:
                    filterBlue(input,filterd);
            }
            Mat processed = ShapeDetectionUtil.processImage(filterd);
            MatOfPoint largestContour = new MatOfPoint();
            List<MatOfPoint> allContours = ShapeDetectionUtil.getAllContours(processed);
            for (MatOfPoint contour: allContours) {
                if(largestContour.empty()){
                    largestContour =contour;
                }
                if( contour.toArray().length>largestContour.toArray().length){
                    largestContour =contour;
                }
            }
            if(largestContour.empty())gameType= Constants.GameConstants.GameType.LEFT;
            else if (Imgproc.boundingRect(largestContour).x>camera_width*0.7) {gameType= Constants.GameConstants.GameType.RIGHT;}
            else gameType = Constants.GameConstants.GameType.CENTER;
            ShapeDetectionUtil.markOuterContour(processed,input);
             filterd.release();
             processed.release();
             telemetry.addData("game type",gameType);
             telemetry.update();
            for (MatOfPoint contour: allContours) {
                contour.release();
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



    public VisionSubsystem(HardwareMap hardwareMap, Telemetry telemetry,    Constants.GameConstants.StartingPosition color) {
        this.color = color;
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
    }

    public void stop(){
        camera.closeCameraDevice();
    }
    public Constants.GameConstants.GameType getGameType(){
        return gameType;
    }
}

