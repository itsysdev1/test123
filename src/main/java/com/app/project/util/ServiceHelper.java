package com.app.project.util;

import com.google.gson.Gson;
import java.util.UUID;

public class ServiceHelper {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return "["+uuidAsString+"]";
    }

    public static String convertToJson(Object object){
        return new Gson().toJson(object);
    }

}
