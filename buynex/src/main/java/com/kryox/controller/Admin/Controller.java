package com.kryox.controller.Admin;


import java.io.File;

import java.util.Map;


import com.cloudinary.utils.ObjectUtils;
import com.kryox.config.AdminConfig;


public class Controller {

    public String imageUpload(File file) {

        if (file == null || !file.exists()) {
            System.out.println("Selected image file not found");
            return null;
        }

        try {

            Map<?, ?> result = AdminConfig.getCloudinary()
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
                System.out.println("Cloudinary URL not found");
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