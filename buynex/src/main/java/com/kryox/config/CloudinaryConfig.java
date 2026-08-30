package com.kryox.config;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfig {
    public static Cloudinary cloudinary;

    public static Cloudinary getCloudinary() {
        if (cloudinary == null) {
            Map<String, Object> config = new HashMap<>();
            config.put("cloud_name", "dbcjcdcm");
            config.put("api_key", "678323231749743");
            config.put("api_secret", "_NjOmwgMBunEa4KDzbxLRxgnXbg");
            config.put("secure", true);

            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }
}