public class HelloWorld {
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println("开始时间：" + start);
        double sum = 1000.0;
        int a, b;
        double c = 0.0;
        b = a = (int) (sum / (2.0 + Math.sqrt(2.0))); //计算45°时，a和b的值
        while (a > 0 && b < sum / 2) {
            c = Math.sqrt(a * a + b * b);
            if (sum < (a + b + c)) { //如果a+b+c > 1000 则减小a的值
                a--;
            } else if (sum > (a + b + c)) {//如果a+b+c <1000 则增加b的值
                b++;
            } else { //如果a+b+c = 1000，得出答案
                break;
            }
        }
        System.out.println("a=" + a + " b=" + b + " c=" + (int) c);
        long end = System.nanoTime();
        System.out.println("结束时间：" + end);
        System.out.println("耗时：" + (end - start) / 1000000.0 + "ms");
        System.out.println("耗时：" + (end - start) + "nano second");
    }
}
