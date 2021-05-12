package indi.simuel.exceptions;

/**
 * @Author simuel_tang
 * @Date 2021/3/6
 * @Time 9:50
 */
public class ProductCategoryOperationException extends RuntimeException {
    public ProductCategoryOperationException(String message) {
        super(message);
    }
}
