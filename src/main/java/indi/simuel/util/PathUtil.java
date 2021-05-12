package indi.simuel.util;

/**
 * @Author simuel_tang
 * @Date 2021/2/21
 * @Time 9:39
 */
public class PathUtil {
    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * 获取项目图片在此电脑下所保存的根路径的前缀
     *
     * @return 图片的存储路径
     */
    public static String getImageBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "E:/work/images/o2o";
        } else {
            basePath = "/Users/work/images";
        }
        basePath = basePath.replace("/", SEPARATOR);
        return basePath;
    }

    /**
     * 将不同的店铺图片存储到不同的文件夹下
     *
     * @param shopId 店铺的唯一表示
     * @return 项目图片的子路径
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/images/item/shop/" + shopId + "/";
        return imagePath.replace("/", SEPARATOR);
    }

    public static void main(String[] args) {
        String basePath = PathUtil.getImageBasePath();
        String shopImagePath = PathUtil.getShopImagePath(1L);
        System.out.println(basePath);
        System.out.println(shopImagePath);
    }
}
