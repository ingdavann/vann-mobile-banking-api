package co.istad.vannbankingapi.util;

public class MediaUtil {
    public static String extractExtension (String name){
        int lastDotIndex = name.lastIndexOf(".");
        return  name.substring(lastDotIndex + 1);
    }
}
