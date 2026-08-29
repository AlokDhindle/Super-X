package com.kryox.controller.Delivery;

import com.kryox.config.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;

public class ImageUploadController {

    public String imageUpload(File file) {
        if (file == null || !file.exists()) {
            System.err.println("[ImageUpload] Error: File is null or does not exist.");
            return null;
        }

        try {
            Cloudinary cloudinary = CloudinaryConfig.getCloudinary();
            if (cloudinary == null) {
                System.err.println("[ImageUpload] Error: Cloudinary instance is null. Verify CloudinaryConfig.java.");
                return null;
            }

            System.out.println("[ImageUpload] Uploading: " + file.getAbsolutePath());
            Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                    "resource_type", "image",
                    "folder", "buynex/partners"
            ));

            System.out.println("[ImageUpload] Response: " + result);

            if (result != null && result.containsKey("secure_url")) {
                String secureUrl = String.valueOf(result.get("secure_url"));
                System.out.println("[ImageUpload] Success URL: " + secureUrl);
                return secureUrl;
            }
        } catch (Exception e) {
            System.err.println("[ImageUpload] Upload failed with exception:");
            e.printStackTrace();
        }
        return null;
    }
}