package indi.simuel.exceptions;

/**
 * @Author simuel_tang
 * @Date 2021/5/4
 * @Time 15:13
 */
public class AreaOperationException extends RuntimeException {
    private static final long serialVersionUID = -1512771573934050550L;

    public AreaOperationException(String msg) {
        super(msg);
    }
}
