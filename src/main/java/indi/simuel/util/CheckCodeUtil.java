package indi.simuel.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author simuel_tang
 * @Date 2021/2/25
 * @Time 13:36
 */
public class CheckCodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取服务器为客户端设置的验证码
        String verifyCodeExpected = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //获取表单中的验证码
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        //比较时忽略大小写
        return verifyCodeActual != null && verifyCodeActual.equalsIgnoreCase(verifyCodeExpected);
    }
}
