package com.netease.yx.dp.udf;

import com.google.gson.*;
import com.netease.yx.dp.model.Coupon;
import com.netease.yx.dp.resolver.CouponResolver;
import com.netease.yx.dp.resolver.PriceResolver;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class OutsidePriceComputeUDF extends UDF {
    private static final Logger logger = LoggerFactory.getLogger(OutsidePriceComputeUDF.class);

    public String evaluate(String htmlStr, String source) {
        JsonParser jsonParser = new JsonParser();
        JsonObject obj = (JsonObject) jsonParser.parse(htmlStr);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("processStatus",1); //1表示解析成功

        String content = obj.get("tb-property").getAsString();

        //价格获取

        List<Double> priceArr = new PriceResolver().resolve(content, source);
        List<Double> resultPrices = new ArrayList(priceArr);
        resultMap.put("priceList", priceArr);


        try {
            //满减规则

            List<Coupon> coupons = new CouponResolver().resolve(content, source);
            resultMap.put("couponList", coupons);

            //计算结果

            for (double price : priceArr) {
                for (Coupon cop : coupons) {
                    logger.info("价格 {} 使用优惠券 {} 之后价格为 {}", price, cop, cop.getResultMoney(price));
                    resultPrices.add(cop.getResultMoney(price));
                }
            }
        } catch (Exception e) {
            resultMap.put("processStatus",0); //0表示解析失败
            resultMap.put("errorMsg","优惠券解析出错");
            logger.error("error {}", e.getStackTrace());
        }


        Collections.sort(resultPrices);

        try{
            resultMap.put("minPrice", resultPrices.get(0));
            logger.info("最低价为:{}", resultPrices.get(0));
        }catch (Exception e){
            resultMap.put("processStatus",0); //0表示解析失败
            resultMap.put("errorMsg","价格list为空");
            logger.error("error {}",e.getStackTrace());
        }

        Gson gson = new Gson();

        return gson.toJson(resultMap);
    }

    public static void main(String[] args) {
//        String content = "";
//        String source = "jd";
//        System.out.println(evaluate(content,source));

    }
}
