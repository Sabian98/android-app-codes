package com.example.taseef_pc.button_example;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Taseef-PC on 1/18/2017.
 */

public class buttonplus extends Button {

    public buttonplus(Context context) {
        super(context);
    }
    public buttonplus(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }
    public buttonplus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }




}
