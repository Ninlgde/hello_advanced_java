import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String str1 = "2011-12-01";
//        String str2 = "2012-01-2";
//        Calendar bef = Calendar.getInstance();
//        Calendar aft = Calendar.getInstance();
//        try {
//            bef.setTime(sdf.parse(str1));
//            aft.setTime(sdf.parse(str2));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
//        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
//        System.out.println(month + result);
//
//        String[] a = {"a", "b", "c", "d", "e"};
//        String[] b = {"1", "2", "3", "4", "5"};
//
//        List<String> la = Arrays.asList(a);
//        List<String> lb = Arrays.asList(b);
//
//        Map<String, String> map = la.stream().collect(Collectors.toMap(x -> x, y -> lb.get(la.indexOf(y))));
//
//        for (Map.Entry<String, String> e : map.entrySet()) {
//            System.out.println("mappppp");
//            System.out.println(e.getKey());
//            System.out.println(e.getValue());
//        }


        String source = "// 中文\n  \t// \ttest\n" +
                "{\n" +
                "  // comment11\n" +
                "  k11: \"v1//1\",\n" +
                "  k12: \"v12\",  \t// comment12\n" +
                "  //comment13  \t\n" +
                "  k13: \"v13\",  \t// \tcomment13  \t\n" +
                "\n" +
                "  /* comment21 */\n" +
                "  k21: \"v21\",\n" +
                "  k22: \"v22\",  \t/* comment22 */\n" +
                "  /*comment23*/  \t\n" +
                "  k23: \"v23\",  \t/* \tcomment23*/  \t\n" +
                "\n" +
                "  /* line311\n" +
                "   * line312\n" +
                "   */\n" +
                "  k31: \"v31\",\n" +
                "  /** line321\n" +
                "   * line322\n" +
                "   */\n" +
                "  k32: \"v32\",\n" +
                "  /**\n" +
                "   * line331\n" +
                "   * line332\n" +
                "   */\n" +
                "  k33: \"v33\",\n" +
                "  ke:0\n" +
                "}";
        System.out.println("source=" + source);
        String result = stripComment(source);
        System.out.println("result=" + result);
//        JSONObject json = new JSONObject(result);
//        System.out.println("json=" + json);
    }

    /**
     * 删除 json 字符串中的注释信息
     * <p>标准的 json 是不允许带注释的，大部分 json 框架都不支持带注释 json 字符串的解析</p>
     * @param source 原始 json 字符串
     * @return 无注释信息的 json 字符串
     */
    public static String stripComment(String source) {
        return source == null ? null : source.replaceAll("\\s*//.*|(?s)\\s*/\\*(.*?)\\*/[ \\t]*", "");
    }
}
