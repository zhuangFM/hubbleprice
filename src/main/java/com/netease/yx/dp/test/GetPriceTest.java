package com.netease.yx.dp.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netease.yx.dp.resolver.PriceResolver;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GetPriceTest {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        JsonObject obj = null;
        try {
            obj = (JsonObject) jsonParser.parse(new FileReader("G:\\workspace_idea\\hubbleprice\\src\\main\\resources\\HtmlSource\\Src.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //价格获取
        String priceStr = "<div class=\"tb-promo-item-bd\"><strong class=\"tb-promo-price\"><em class=\"tb-rmb\" data-spm-anchor-id=\"2013.1.iteminfo.i1.602c2aa69yPa4H\">¥</em><em id=\"J_PromoPriceNum\" class=\"tb-rmb-num\" data-spm-anchor-id=\"2013.1.iteminfo.i0.602c2aa69yPa4H\">208.00-228.00</em></strong><span id=\"J_PromoType\" class=\"tb-promo-type\">真皮馆</span><span id=\"J_PromoTips\" class=\"tb-promo-tips\"></span></div>";
//        boolean isRangePrice = priceStr.contains("-");
//        List<Double> resultPrices = new ArrayList();


//        Pattern p = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
//        Matcher m = p.matcher(priceStr);
//        while (m.find()) {
//            resultPrices.add(Double.valueOf(m.group()));
//        }
//
//        System.out.println(isRangePrice ? "区间段" : "非区间价格段");
//        System.out.println(resultPrices);


//        String content = obj.get("tb-property").getAsString();
        System.out.println(new PriceResolver().resolve(priceStr,"taobao"));


    }
}
