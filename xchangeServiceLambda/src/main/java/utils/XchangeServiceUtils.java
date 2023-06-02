package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public final class XchangeServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    static final int PLAYLIST_ID_LENGTH = 5;

    private XchangeServiceUtils() {
    }

    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    public static String generateId() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public static Boolean isValidZipCode(String zipCode) {
        String pattern = "^\\d{5}$";
        return zipCode.matches(pattern);
    }

    public static Set<String> formatImages(Set<String> imageReferences) {
        if(imageReferences == null) {
            return new HashSet<>();
        }
        Set<String> imageUrls = new HashSet<>();
        String s3Bucket = "https://nss-s3-c02-02-u5-project-hillarymartin.s3.us-east-2.amazonaws.com/";
        for(String reference : imageReferences) {
            String imageUrl = s3Bucket + reference;
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }
}