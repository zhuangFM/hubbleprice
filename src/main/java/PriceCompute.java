import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceCompute {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        JsonObject obj = null;
        try {
            obj = (JsonObject) jsonParser.parse(new FileReader("G:\\workspace_idea\\hubbleprice\\src\\main\\resources\\HtmlSource\\Src.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //价格获取

        String content = obj.get("tb-property").getAsString();
        Document document = Jsoup.parse(content);
        List<Double> priceArr = new ArrayList<Double>();
        List<Double> resultPrices = new ArrayList<Double>();
        for (Element ele : document.getElementsByClass("tm-price")) {
            try {
                double price = Double.valueOf(ele.text());
                System.out.println(String.format("价格%d:%f", priceArr.size(), price));
                priceArr.add(price);
                resultPrices.add(price);
            }catch (NumberFormatException e){
                System.out.println(String.format("价格 %s 为区间段 无法解析",ele.text()));
                return;
            }
        }
        //满减规则

        List<String> strs = new ArrayList<String>();
        List<Coupon> coupons = new ArrayList<Coupon>();
        Pattern p = Pattern.compile("(每满|满).*?((\\d+\\.\\d+)|(\\d+)).*?(元)?减.*?((\\d+\\.\\d+)|(\\d+)).*?(元)?");
//        Pattern p = Pattern.compile("(每满|满).*?减\\s*((\\d+\\.\\d+)|(\\d+))"); //这条也行
        Matcher m = p.matcher(content);
        while (m.find()) {
            strs.add(m.group());
        }
        System.out.println(strs.size()==0?"无满减优惠":strs);

        Pattern p1 = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
        for (String str : strs) {
            double[] moneyArr = new double[2];
            int i = 0;
            str = str.replaceAll(" ","");
            Matcher m1 = p1.matcher(str);
            while (m1.find()) {
                moneyArr[i++] = Double.valueOf(m1.group());
            }
            System.out.println(String.format("优惠券:%s", str));
            Coupon coupon = new Coupon(moneyArr[0], moneyArr[1], str.contains("每") ? 2 : 1);
            coupons.add(coupon);
        }
        //计算结果


        for(double price : priceArr){
            for (Coupon cop : coupons){
                System.out.println(String.format("价格%f 使用优惠券 %s 之后价格为%f",price,cop,cop.getResultMoney(price)));
                resultPrices.add(cop.getResultMoney(price));
            }
        }

        Collections.sort(resultPrices);
        System.out.println(String.format("最低价为:%f",resultPrices.get(0)));
    }
}
