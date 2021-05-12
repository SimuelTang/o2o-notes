import java.util.Scanner;

/**
 * @Author simuel_tang
 * @Date 2021/4/9
 * @Time 15:04
 * 
 * 尖峰数组，长度大于3，中间比两头都大
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 3) {
            System.out.println(0);
        } else {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            
        }
    }
}
