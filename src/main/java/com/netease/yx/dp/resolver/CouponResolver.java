package com.netease.yx.dp.resolver;

import com.netease.yx.dp.model.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CouponResolver implements Resolver {
    private static final Logger logger = LoggerFactory.getLogger(CouponResolver.class);

    @Override
    public List resolve(String content, String source) {
        List<Coupon> coupons = new ArrayList<>();
        Pattern pCoupon1 = Pattern.compile("(每满|满)(\\s)*?((\\d+\\.\\d+)|(\\d+))(\\s)*?(元)?[^\\d]*?减(\\s)*?((\\d+\\.\\d+)|(\\d+))(\\s)*?(元)?");
        Pattern pCoupon2 = Pattern.compile("((\\d+\\.\\d+)|(\\d+))(元)[^A-Za-z\\d]*?满((\\d+\\.\\d+)|(\\d+))(元)?(可用)");

        List<String> strs = new ArrayList<>();
        Matcher mCoupon1 = pCoupon1.matcher(content);
        while (mCoupon1.find()) {
            logger.info("正则表达式1 匹配优惠券原始内容为 {}",mCoupon1.group());
            strs.add(mCoupon1.group());
            coupons.add(getCoupon(mCoupon1.group(), 1));
        }

        Matcher mCoupon2 = pCoupon2.matcher(content);
        while (mCoupon2.find()) {
            logger.info("正则表达式2 匹配优惠券原始内容为 {}",mCoupon2.group());
            strs.add(mCoupon2.group());
            coupons.add(getCoupon(mCoupon2.group(), 2));
        }

        logger.info("优惠券list 为 : {}",strs.size() == 0 ? "无满减优惠" : strs);
        return coupons;
    }

    /**
     * @param couponStr
     * @param type      1- 每满|满xx减xx 2- xx元 满xx可用
     * @return
     */
    private Coupon getCoupon(String couponStr, int type) {
        double[] moneyArr = new double[2];
        int i = 0;
        couponStr = couponStr.replaceAll(" ", "");
        Pattern pPrice = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
        Matcher mPrice = pPrice.matcher(couponStr);
        while (mPrice.find()) {
            moneyArr[i++] = Double.valueOf(mPrice.group());
        }

        logger.info("转化为优惠券对象前 {} 优惠券类型为 {}",couponStr,type);

        Coupon coupon = null;
        switch (type) {
            case 1:
                coupon = new Coupon(moneyArr[0], moneyArr[1], couponStr.contains("每") ? 2 : 1);
                break;
            case 2:
                coupon = new Coupon(moneyArr[1], moneyArr[0], couponStr.contains("每") ? 2 : 1);
                break;
        }
        return coupon;
    }

}
