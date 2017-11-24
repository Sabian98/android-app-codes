package com.example.taseef_pc.list_custom;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Taseef-PC on 7/3/2016.
 */

public class prodAdapter extends ArrayAdapter<prod_class> {
    private List<prod_class>prods;
public prodAdapter(Context context, int resource, List objects)
{
    super(context,resource,objects);
    prods=objects;
}
public View getView(int position,View convertView,ViewGroup parent)
{
    if(convertView==null)
    {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
    }
    prod_class pro=prods.get(position);
    TextView nameText=(TextView)convertView.findViewById(R.id.textView);
    nameText.setText(pro.getName());

    NumberFormat formatter=NumberFormat.getCurrencyInstance();
    String price=formatter.format(pro.getPrice());
    TextView priceText=(TextView)convertView.findViewById(R.id.textView2);
    priceText.setText(price);
    ImageView iv=(ImageView)convertView.findViewById(R.id.imageView);
    Bitmap bmp=getBitMapFromasset(pro.getProd_id());
    iv.setImageBitmap(bmp);
    return convertView;
}
private Bitmap getBitMapFromasset(String prodID)
{
    AssetManager assetManager=getContext().getAssets();
    InputStream inputStream=null;
    try {
        inputStream =assetManager.open(prodID+".png");
        return BitmapFactory.decodeStream(inputStream);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}
}
