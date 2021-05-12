package indi.simuel.util;

/**
 * @Author simuel_tang
 * @Date 2021/3/2
 * @Time 20:17
 */
public class PageCalculatorUtil {
    public static int pageIndexToRow(int pageIndex, int pageSize) {
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }
}
