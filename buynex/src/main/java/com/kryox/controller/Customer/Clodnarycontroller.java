

package com.kryox.controller.Customer;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kryox.config.Coustomerconfig;

public class Clodnarycontroller {

    public static String imageUpload(File file) {

        Cloudinary cloudinary = Coustomerconfig.getCloudinary();

        try {

            Map<String, Object> result = cloudinary.uploader()
                    .upload(file, ObjectUtils.asMap(
                            "resource_type", "image"
                    ));

            System.out.println(result);

            String url = String.valueOf(result.get("secure_url"));

            System.out.println("Uploaded URL: " + url);

            return url;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
}
}


