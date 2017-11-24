package com.example.taseef_pc.virtual_chrome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public Bitmap cameraImage,bmp;
    boolean img_bw;
    public static int d=0;

    public static final int CAMMERA_REQUEST = 10;
    private ImageView imgSpecimenPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSpecimenPhoto =(ImageView) findViewById(R.id.imageSpecimenPhoto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

               switch(id)
               {
                   case R.id.action_settings:
                       Toast.makeText(getApplicationContext(),"The app aims to create a puzzle of the world around you. Just choose your puzzle size and prove how good you can perceive!!!", Toast.LENGTH_LONG).show();
               }

        return super.onOptionsItemSelected(item);
    }



    public void bluetored (View v)
    {   d=1;

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMMERA_REQUEST);
    }
    public void redtoblue (View v)
    {   d=2;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMMERA_REQUEST);
    }
    public void greentoblue (View v)
    {   d=3;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMMERA_REQUEST);
    }
    public void bluetogreen (View v)
    {   d=4;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMMERA_REQUEST);
    }
    public void greentored (View v)
    {   d=5;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMMERA_REQUEST);
    }
    public void redtogreen (View v)
    {   d=6;
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

        //textView.setText("d"+d);
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
                if(d==1&&B>G+10&&B>R+10){
                    bmOut.setPixel(x,y,Color.argb(A,B,0,0));
                }
                else if(d==2&&R>G+10&&R>B+10){
                    bmOut.setPixel(x,y,Color.argb(A,0,0,R));
                }
                else if(d==3&&G>B+10&&G>R+10){
                    bmOut.setPixel(x,y,Color.argb(A,0,0,G));
                }
                else if(d==4&&B>G+10&&B>R+10){
                    bmOut.setPixel(x,y,Color.argb(A,0,B,0));
                }
                else if(d==5&&G>R+10&&G>B+10){
                    bmOut.setPixel(x,y,Color.argb(A,G,0,0));
                }
                else if(d==6&&R>G+10&&R>B+10){
                    bmOut.setPixel(x,y,Color.argb(A,0,R,0));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==CAMMERA_REQUEST) {

                cameraImage = (Bitmap) data.getExtras().get("data");


                bmp=doGreyscale(cameraImage);

                imgSpecimenPhoto.setImageBitmap(bmp);


            }
        }
    }
}
