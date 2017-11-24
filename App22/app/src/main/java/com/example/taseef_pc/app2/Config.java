package com.example.taseef_pc.app2;

/**
 * Created by Taseef-PC on 1/1/2017.
 */

public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://192.168.1.105/Foodconnect/addDonor.php";
    public static final String URL_GET_ALL ="http://192.168.1.105/Foodconnect/getAllDonor.php";
    public static final String URL_GET_DONOR = "http://192.168.1.105/Foodconnect/getDonor.php";
   // public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
   // public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_DON_NAME = "Name";
    public static final String KEY_DON_PLACE = "Place";
    public static final String KEY_EMP_DATE = "Date";
   // public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_DATE = "Date";
    public static final String TAG_NAME = "Name";
    public static final String TAG_PLACE = "Place";
  //  public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "Name";

}
