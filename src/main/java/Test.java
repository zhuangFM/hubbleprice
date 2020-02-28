import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args){
        List<String> strs = new ArrayList<String>();
        String content = "满 199 元 减70.1   每满 199  减71   满 199.1 减71.11 元 ";
//        Pattern p = Pattern.compile("(每满|满).*?\\d+.*?(元)?减.*?\\d+.*?(元)?");
//        Pattern p = Pattern.compile("(每满|满).*?((\\d+\\.\\d+)|(\\d+)).*?(元)?减.*?((\\d+\\.\\d+)|(\\d+)).*?(元)?");
        Pattern p = Pattern.compile("(每满|满).*?减\\s*((\\d+\\.\\d+)|(\\d+))");
        Matcher m = p.matcher(content);
        while (m.find()) {
            strs.add(m.group());
        }
        System.out.println(strs);

//        Pattern p1 = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
//        for (String str : strs) {
//            double[] moneyArr = new double[2];
//            int i = 0;
//            str = str.replaceAll(" ","");
//            Matcher m1 = p1.matcher(str);
//            while (m1.find()) {
//                moneyArr[i++] = Double.valueOf(m1.group());
//            }
//            Coupon coupon = new Coupon(moneyArr[0], moneyArr[1], str.contains("每") ? 2 : 1);
//            System.out.println(String.format("优惠券:%s", str));
//            System.out.println(coupon);
//        }

    }
}
