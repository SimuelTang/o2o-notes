import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author simuel_tang
 * @Date 2021/4/9
 * @Time 15:34
 */
public class Test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(countZero(n, m));
    }

    private static int countZero(int n, int m) {
        int ans;
        int stepN = n;
        List<String> curr = new ArrayList<>();
        curr.add(String.valueOf(stepN));
        for (int i = n - 1; i > 0; i--) {
            curr = getStep(curr, i);
        }
        List<Integer> num = toMnary(stepN, m);
        ans = count(num);
        return ans;
    }
    
    private static List<String> getStep(List<String> curr, int num) {
        List<String> ans = new ArrayList<>();
        
        return ans;
    }

    private static List<Integer> toMnary(int n, int m) {
        List<Integer> ans = new ArrayList<>();
        while (n != 0) {
            ans.add(n % m);
            n /= m;
        }
        return ans;
    }

    private static int count(List<Integer> num) {
        int cnt = 0;
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i) > 0) break;
            cnt++;
        }
        return cnt;
    }
}
