import java.util.Scanner;

/**
 * @Author simuel_tang
 * @Date 2021/4/9
 * @Time 15:16
 */
public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 10) {
            System.out.println(n);
        } else {
            int ret = n, i = 2;
            while (ret < Integer.MAX_VALUE) {
                String str = String.valueOf(ret);
                if ((str.contains("0") || str.contains("1")) && existX(str)) {
                    System.out.println(str);
                    break;
                } else {
                    ret = n * i;    
                    i++;
                }
            }
            if (ret >= Integer.MAX_VALUE) System.out.println(-1);
        }
    }

    private static boolean existX(String str) {
        char[] cs = str.toCharArray();
        int firstNum = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '0' || cs[i] == '1') continue;
            if (firstNum == 0) firstNum = Integer.parseInt(String.valueOf(cs[i]));
            if (firstNum != Integer.parseInt(String.valueOf(cs[i]))) return false;
        }
        return true;
    }
}
