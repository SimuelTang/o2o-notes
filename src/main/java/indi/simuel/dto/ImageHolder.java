package indi.simuel.dto;

import java.io.InputStream;

/**
 * @Author simuel_tang
 * @Date 2021/2/22
 * @Time 8:56
 */
public class ImageHolder {
    private String imageName;
    private InputStream image;

    public ImageHolder() {
    }

    public ImageHolder(String imageName, InputStream image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public InputStream getImage() {
        return image;
    }
}
