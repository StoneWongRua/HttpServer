package com.tong.http;

public enum FileType {
    /**
     * JEPG.
     */
    IMAGE_JPEG("FFD8FF"),

    /**
     * PNG.
     */
    IMAGE_PNG("89504E47"),

    /**
     * GIF.
     */
    IMAGE_GIF("47494638"),

    /**
     * Windows Bitmap.
     */
    IMAGE_BMP("424D");

    private String value = "";


    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
