package com.kryox.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public class AdminConfig {
    public static Cloudinary cloudinary;
    public static Cloudinary getCloudinary(){

        if(cloudinary==null){
            Map<String,Object> config=new HashMap<>();

            config.put("cloud_name","dkmodabf");
            config.put("api_key","241836831753572");
            config.put("api_secret","sBaLnZ3bH7Dt8Yz_7NkFRJTzO1U");
            config.put("secure","true");

            cloudinary=new Cloudinary(config);

        }
        return cloudinary;
    
 }
}