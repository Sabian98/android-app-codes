package com.example.taseef_pc.switch_em_all;


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
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Main22Activity extends Activity implements CvCameraViewListener,  View.OnTouchListener {

    private static final String  TAG = "Sample::Puzzle25::Activity";

    private CameraBridgeViewBase mOpenCvCameraView;
    private Puzzle25Processor    mPuzzle25;
    private MenuItem             mItemHideNumbers;
    private MenuItem             mItemStartNewGame;


    private int                  mGameWidth;
    private int                  mGameHeight;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private ShakeDetector mShaker;
    private int d=1;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    //Log.i(TAG, "OpenCV loaded successfully");

                    /* Now enable camera view to start receiving frames */
                    mOpenCvCameraView.setOnTouchListener(Main22Activity.this);
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

        //Log.d(TAG, "Creating and setting view");
        mOpenCvCameraView = (CameraBridgeViewBase) new JavaCameraView(this, -1);
        setContentView(mOpenCvCameraView);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mPuzzle25 = new Puzzle25Processor(d);
        mPuzzle25.prepareNewGame();
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        mShaker = new ShakeDetector(this);
        mShaker.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            public void onShake() {

                if(d==3){d=0;}
                d++;
                new Puzzle25Processor(d);

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
            // Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            // Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Log.i(TAG, "called onCreateOptionsMenu");
        mItemHideNumbers = menu.add("Show/hide tile numbers");
        mItemStartNewGame = menu.add("Start new game");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Log.i(TAG, "Menu Item selected " + item);
        if (item == mItemStartNewGame) {
            /* We need to start new game */
            mPuzzle25.prepareNewGame();
        } else if (item == mItemHideNumbers) {
            /* We need to enable or disable drawing of the tile numbers */
            mPuzzle25.toggleTileNumbers();
        }
        return true;
    }

    public void onCameraViewStarted(int width, int height) {
        mGameWidth = width;
        mGameHeight = height;
        mPuzzle25.prepareGameSize(width, height);
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
            mPuzzle25.deliverTouchEvent(xpos, ypos);
        }

        return false;
    }

    public Mat onCameraFrame(Mat inputFrame) {
        return mPuzzle25.puzzleFrame(inputFrame);
    }
}