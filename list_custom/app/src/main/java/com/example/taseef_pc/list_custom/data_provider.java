package com.example.taseef_pc.list_custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Taseef-PC on 7/2/2016.
 */
public final class data_provider {
    public static List<prod_class>prodClassList= new ArrayList<>();

    public static Map<String,prod_class> stringprod_classMap=new HashMap<>();


   static  {
        addproduct("jacket101","xsdce sd","ff wefwe",76);
        addproduct("java_mega","xthtre shtd","fhtf wefwe",77);
       addproduct("java_kurt","xtytryhhtrtd","fhutf wefwe",76);
    }

    public static List<String>getprodnames()
    {
       List<String>list=new ArrayList<>();
        for(prod_class pro:prodClassList)
        {
       list.add(pro.getName());
        }
    return list;
    }

    private static void addproduct(String itemId, String name,
                                    String description, double price)
    {
        prod_class item = new prod_class(itemId, name, description, price);
        prodClassList.add(item);
        stringprod_classMap.put(itemId, item);
    }
public static List<prod_class>getFilteredList(String srchstsr)
{
    List<prod_class>filterList=new ArrayList<>();
    for(prod_class pro:prodClassList)
    {
        if(pro.getProd_id().contains(srchstsr))
        {
            filterList.add(pro);
        }
    }
    return filterList;
}
}
