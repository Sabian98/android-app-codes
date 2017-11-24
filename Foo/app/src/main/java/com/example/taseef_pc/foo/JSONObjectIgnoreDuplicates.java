package com.example.taseef_pc.foo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Taseef-PC on 2/17/2017.
 */

public class JSONObjectIgnoreDuplicates extends JSONObject {


    public JSONObjectIgnoreDuplicates(String json) throws JSONException {
        super(json);
    }

    public JSONObject putOnce(String key, Object value) throws JSONException {
        Object storedValue;
        if (key != null && value != null) {
            if ((storedValue = this.opt(key)) != null ) {
                if(!storedValue.equals(value))                          //Only through Exception for different values with same key

                    Log.d("not accepted","n");


                else
                    return this;
            }
            this.put(key, value);
        }
        return this;
    }

}

