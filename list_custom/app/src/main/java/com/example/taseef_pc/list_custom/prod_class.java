package com.example.taseef_pc.list_custom;

/**
 * Created by Taseef-PC on 7/2/2016.
 */
public class prod_class {
    private String prod_id;
    private String name;
    private String description;
    private  double price;

     public   String getProd_id()
     {
          return prod_id;
     }
    public String getName()
    {
        return name;
    }
    public  double  getPrice()
    {
        return  price;
    }
    public String getDescription()
    {
        return description;
    }
    public  prod_class(String prod_id,String name,String description,double price)
    {
        this.prod_id=prod_id;
        this.price=price;
        this.description=description;
        this.name=name;
    }
}
