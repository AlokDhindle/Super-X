package com.kryox.controller.Admin;


import java.io.File;
import java.util.Map;


import com.cloudinary.utils.ObjectUtils;
import com.kryox.config.Config;

public class Controller {

    public String imageUpload(File file) {

        if (file == null || !file.exists()) {
            System.out.println("Selected image file सापडली नाही");
            return null;
        }

        try {

            Map<?, ?> result = Config.getCloudinary()
                    .uploader()
                    .upload(
                            file,
                            ObjectUtils.asMap(
                                    "resource_type", "image",
                                    "folder", "buynex/users"
                            )
                    );

            Object secureUrl = result.get("secure_url");

            if (secureUrl == null) {
                System.out.println("Cloudinary URL मिळाली नाही");
                return null;
            }

            String url = secureUrl.toString();

            System.out.println("Uploaded URL: " + url);

            return url;

        } catch (Exception e) {

            System.out.println(
                    "Cloudinary upload error: " + e.getMessage()
            );

            e.printStackTrace();

            return null;
        }
    }
}