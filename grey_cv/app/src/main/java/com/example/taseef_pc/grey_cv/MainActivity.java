package com.example.taseef_pc.grey_cv;



import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener;
import org.opencv.android.JavaCameraView;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity implements CvCameraViewListener, View.OnTouchListener {

    private static final String  TAG = "Sample::Puzzle15::Activity";
    //int h=3;
    private CameraBridgeViewBase mOpenCvCameraView;
    private Puzzle15Processor    mPuzzle15;
    private p15_1                shurul;
    private MenuItem             mItemHideNumbers;
    private MenuItem             mItemStartNewGame;
    private int d=1;

    private int                  mGameWidth;
    private int                  mGameHeight;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");

                    /* Now enable camera view to start receiving frames */
                    mOpenCvCameraView.setOnTouchListener(MainActivity.this);
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Log.d(TAG, "Creating and setting view");
        mOpenCvCameraView = (CameraBridgeViewBase) new JavaCameraView(this, -1);
        setContentView(mOpenCvCameraView);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);

        //CREATED LATER FOR SHAKE-EVENT

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
                mPuzzle15 = new Puzzle15Processor(d);
                mPuzzle15.prepareNewGame();

            }

        });
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
        mItemHideNumbers = menu.add("Show/hide tile numbers");
        mItemStartNewGame = menu.add("Start new game");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "Menu Item selected " + item);
        if (item == mItemStartNewGame) {
            /* We need to start new game */
            mPuzzle15.prepareNewGame();
        } else if (item == mItemHideNumbers) {
            /* We need to enable or disable drawing of the tile numbers */
            mPuzzle15.toggleTileNumbers();
        }
        return true;
    }

    public void onCameraViewStarted(int width, int height) {
        mGameWidth = width;
        mGameHeight = height;
        mPuzzle15.prepareGameSize(width, height);
    }

    public void onCameraViewStopped() {
    }

    public boolean onTouch(View view, MotionEvent event) {
        int xpos, ypos;

        xpos = (view.getWidth() - mGameWidth) / 2;
        xpos = (int)event.getX() - xpos;

        ypos = (view.getHeight() - mGameHeight) / 2;
        ypos = (int)event.getY() - ypos;

        if (xpos >=0 && xpos <= mGameWidth && ypos >=0  && ypos <= mGameHeight) {
            /* click is inside the picture. Deliver this event to processor */
            mPuzzle15.deliverTouchEvent(xpos, ypos);
        }

        return false;
    }

    public Mat onCameraFrame(Mat inputFrame) {
        return mPuzzle15.puzzleFrame(inputFrame);
    }

    public void handleShakeEvent(int count) {
    if (count!=0){

         d++;

    }}

    }