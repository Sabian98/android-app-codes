package com.example.taseef_pc.facedetect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.samples.puzzle15.R;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.imgproc.Imgproc.threshold;


public class FdActivity extends AppCompatActivity {
    public Bitmap cameraImage,bmp;
    boolean img_bw;
    public Mat rgb_img, gray_img;
    public static final int CAMMERA_REQUEST = 10;
    private ImageView imgSpecimenPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        imgSpecimenPhoto =(ImageView) findViewById(R.id.imageSpecimenPhoto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void btnTakePhotoClicked (View v)
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMMERA_REQUEST);
    }

    public static Bitmap doGreyscale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                if(G>R&&G>B)
                {
                    bmOut.setPixel(x, y, Color.argb(A, G, 0, 0));

                }


                else if(R>G&&R>B)
                {
                    bmOut.setPixel(x, y, Color.argb(A, 0, 0, R));

                }
                else if(B>G&&R<B)
                {
                    bmOut.setPixel(x, y, Color.argb(A, 0, B, 0));

                }


                else{
                    bmOut.setPixel(x, y, Color.argb(A,R, G, B));

                }
            }
        }



        // return final image
        return bmOut;
    }
    /*public static Bitmap doInvert(Bitmap src) {
        // create new bitmap with the same settings as source bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A, R, G, B;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();

        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // get one pixel
                pixelColor = src.getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                // inverting byte for each R/G/B channel
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final bitmap
        return bmOut;
    }*/
    private Mat findLargestRectangle(Mat original_image) {
        Mat imgSource = original_image;
        //Mat untouched = original_image.clone();
        //convert the image to black and white
        Imgproc.cvtColor(imgSource, imgSource, Imgproc.COLOR_BGR2GRAY);
        //threshold(imgSource,imgSource,255,128,1);
        //convert the image to black and white does (8 bit)
        Imgproc.Canny(imgSource, imgSource, 50, 50);

        //apply gaussian blur to smoothen lines of dots
        Imgproc.GaussianBlur(imgSource, imgSource, new Size(5, 5), 1);

        //find the contours
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imgSource, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_TC89_KCOS);

        double maxArea = -1;
        int maxAreaIdx = -1;
        MatOfPoint temp_contour = contours.get(0); //the largest is at the index 0 for starting point
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        MatOfPoint2f maxCurve = new MatOfPoint2f();
        List<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
        for (int idx = 0; idx < contours.size(); idx++) {
            temp_contour = contours.get(idx);
            double contourarea = Imgproc.contourArea(temp_contour);
            //compare this contour to the previous largest contour found
            if (contourarea > maxArea) {
                //check if this contour is a square
                MatOfPoint2f new_mat = new MatOfPoint2f( temp_contour.toArray() );
                int contourSize = (int)temp_contour.total();
                Imgproc.approxPolyDP(new_mat, approxCurve, contourSize*0.05, true);
                if (approxCurve.total() == 4) {
                    maxCurve = approxCurve;
                    maxArea = contourarea;
                    maxAreaIdx = idx;
                    largest_contours.add(temp_contour);
                }
            }
        }


        //create the new image here using the largest detected square
        Mat new_image = new Mat(imgSource.size(), CvType.CV_8U); //we will create a new black blank image with the largest contour
        Imgproc.cvtColor(new_image, new_image, Imgproc.COLOR_BayerBG2RGB);
        Imgproc.drawContours(new_image, contours, maxAreaIdx, new Scalar(255, 255, 255), 1); //will draw the largest square/rectangle


        System.out.println("points length" + temp_contour.toArray().length);

        if( temp_contour.toArray().length == 5)
        {
            System.out.println("Pentagon");
            TextView temp_text = (TextView)findViewById(R.id.temp_text);
            temp_text.setText("\n Pentagon"+ temp_contour.toArray().length);

        }
        else if(temp_contour.toArray().length > 5)
        {
            System.out.println("Circle");
            TextView temp_text = (TextView)findViewById(R.id.temp_text);
            temp_text.setText("\n Circle " + temp_contour.toArray().length);
        }
        else if(temp_contour.toArray().length == 4)
        {
            System.out.println("Square");

            double temp_double[] = maxCurve.get(0, 0);
            Point p1 = new Point(temp_double[0], temp_double[1]);
            Imgproc.circle(new_image, new Point(p1.x, p1.y), 20, new Scalar(255, 0, 0), 5); //p1 is colored red
            String temp_string = "Point 1: (" + p1.x + ", " + p1.y + ")";


            temp_double = maxCurve.get(1, 0);
            Point p2 = new Point(temp_double[0], temp_double[1]);
            Imgproc.circle(new_image, new Point(p2.x, p2.y), 20, new Scalar(0, 255, 0), 5); //p2 is colored green
            temp_string += "\nPoint 2: (" + p2.x + ", " + p2.y + ")";

            temp_double = maxCurve.get(2, 0);
            Point p3 = new Point(temp_double[0], temp_double[1]);
            Imgproc.circle(new_image, new Point(p3.x, p3.y), 20, new Scalar(0, 0, 255), 5); //p3 is colored blue
            temp_string += "\nPoint 3: (" + p3.x + ", " + p3.y + ")";

            temp_double = maxCurve.get(3, 0);
            Point p4 = new Point(temp_double[0], temp_double[1]);
            Imgproc.circle(new_image, new Point(p4.x, p4.y), 20, new Scalar(0, 255, 255), 5); //p1 is colored violet
            temp_string += "\nPoint 4: (" + p4.x + ", " + p4.y + ")";

            TextView temp_text = (TextView)findViewById(R.id.temp_text);
            temp_text.setText(temp_string+"\n Square"+ temp_contour.toArray().length);
        }
        else if(temp_contour.toArray().length == 3)
        {
            System.out.println("Triangle");
            TextView temp_text = (TextView)findViewById(R.id.temp_text);
            temp_text.setText("\n Triangle"+ temp_contour.toArray().length);
        }




        return new_image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==CAMMERA_REQUEST) {

                cameraImage = (Bitmap) data.getExtras().get("data");

                // bmp=doGreyscale(cameraImage);

                //imgSpecimenPhoto.setImageBitmap(bmp);

                if (OpenCVLoader.initDebug()) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;


                    /****************** Problem starts HERE ******************
                     Last point reachable before Logcat states:
                     W/System.err(4460):java.lang.IllegalArgumentException: mat == null

                     After this point, the program doesn't crash, but expected results
                     in the imageView (last line of code) do not result.
                     **********************************************************/
                    Mat rgb_img = new Mat();
                    Mat gray_img=new Mat();

                    Utils.bitmapToMat(cameraImage, rgb_img);
                    Imgproc.cvtColor(rgb_img, rgb_img, Imgproc.COLOR_RGB2HSV, 4 );
                    rgb_img=findLargestRectangle(rgb_img);

                    //Imgproc.Canny(grayFrame, binaryFrame, 80, 90);
                    //Imgproc.Canny(rgb_img, binaryFrame, 0, 50);
                    //Imgproc.findContours(binaryFrame.clone(), contours, mHierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
                    /*Imgproc.cvtColor(rgb_img, rgb_img, Imgproc.COLOR_RGBA2GRAY);
                    //threshold(gray_img,gray_img,128,255,1);
                    Imgproc.Canny(rgb_img, rgb_img, 50, 200);
                    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
                    Mat hierarchy = new Mat();
                    Imgproc.findContours(rgb_img, contours, hierarchy, Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);
                    for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
                        Imgproc.drawContours(rgb_img, contours, contourIdx, new Scalar(0, 0, 255), -1);
                    }

                    /*int width= cameraImage.getWidth();
                    int height=cameraImage.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(rgb_img, bmp);

                          */
                    int width= cameraImage.getWidth();
                    int height=cameraImage.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(rgb_img, bmp);

                    //Bitmap tempBmp1 = Bitmap.createBitmap(cameraImage.getWidth(), cameraImage.getHeight(), cameraImage.getConfig());

                    // Utils.matToBitmap(rgb_img, tempBmp1);

                    imgSpecimenPhoto.setImageBitmap(bmp);
                }
            }
        }
    }
}
