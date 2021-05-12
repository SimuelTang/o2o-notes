package indi.simuel.util;

import indi.simuel.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * @Author simuel_tang
 * @Date 2021/2/21
 * @Time 9:51
 */
public class ImageUtil {
    private static final String basePath = Objects.requireNonNull
            (Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final Random r = new Random();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 这个类用于生成缩略图
     *
     * @param imageHolder 存储当前图片的容器
     * @param targetPath  当前图片希望存储的目标路径
     * @return 行对路径位置
     */
    public static String generateThumbnail(ImageHolder imageHolder, String targetPath) {
        // 为图片生成随机的名字
        String realFileName = getRandomFileName();
        // 获取该文件的后缀
        String extension = getFileExtention(imageHolder.getImageName());
        // 通过传入的 targetPath 创建绝对路径
        makeDir(targetPath);
        // 图片的相对路径（包括图片名字）
        String relativeAddr = targetPath + realFileName + extension;
        // 图片存储的绝对路径
        File dest = new File(PathUtil.getImageBasePath() + relativeAddr);
        try {
            Thumbnails.of(imageHolder.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    private static String getFileExtention(String imageName) {
        return imageName.substring(imageName.lastIndexOf("."));
    }


    private static void makeDir(String targetPath) {
        String absolutPath = PathUtil.getImageBasePath() + targetPath;
        File dirPath = new File(absolutPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    private static String getRandomFileName() {
        //随机的5位数
        int ram = r.nextInt(90000) + 10000;
        String currTime = sdf.format(new Date());
        return currTime + ram;
    }

    /**
     * 删除图片文件
     *
     * @param imagePath 图片的相对路径
     */
    public static void deleteImage(String imagePath) {
        File fileOrPath = new File(PathUtil.getImageBasePath() + imagePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                assert (files != null);
                for (File file : files) {
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static String generateNormalImg(ImageHolder productImgHolder, String dest) {
        // 生成随机文件名
        String filename = getRandomFileName();
        // 获取文件的扩展名
        String extention = getFileExtention(productImgHolder.getImageName());
        // 如果目标路径不存在，则创建
        makeDir(dest);
        // 生成存储的相对路径
        String relativeAddr = dest + filename + extention;
        // 文件要保存到的目标路径
        File file = new File(PathUtil.getImageBasePath() + relativeAddr);
        try {
            Thumbnails.of(productImgHolder.getImage()).size(330, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(file);
        } catch (IOException e) {
            throw new RuntimeException("图片创建失败");
        }
        return relativeAddr;
    }
}
