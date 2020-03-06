package com.netease.yx.dp.test;

import com.netease.yx.dp.model.Coupon;
import com.netease.yx.dp.resolver.CouponResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CouponTest {
    public static void main(String[] args){
        List<String> strs = new ArrayList<String>();
//        String content = "跨店铺满减";
//        String couponStr = "本店活动</dt><dd>满200元减20元</dd><a class=\\\"more\\\">更多优惠<s>\uE62B</s></a></div><div class=\\\"tm-floater-Box  hidden\\\"><div class=\\\"floater fold\\\">     <div class=\\\"hd\\\">         <em class=\\\"title \\\">本店活动</em>         满200减20元                               到2020";
//        String couponStr = "每满399元，可减50元现金，包邮（限中国内地） 包邮（限中国内地）";
//        String couponStr = "满199元减30元，满299元减50元，满499元减100元";
        String couponStr = "5元店铺红包，满5.01元可用 80元店铺优惠券，满600元可用";
//        String couponStr = "满额返券</em><em class=\\\"hl_red\\\">购买食品部分品类满1元即返茶叶糖巧169减30优惠券</em>&nbsp;<a h";
//        String couponStr = "满19.90元另加9.90元，或满29.90元另加19.90元，即可在购物车换购热销商品";
//        Pattern pCoupon1 =Pattern.compile("(每满|满)(\\s)*?((\\d+\\.\\d+)|(\\d+))(\\s)*?(元)?[^\\d]*?减(\\s)*?((\\d+\\.\\d+)|(\\d+))(\\s)*?(元)?");
        Pattern pCoupon1 =Pattern.compile("((\\d+\\.\\d+)|(\\d+))(元)[^A-Za-z\\d]*?满((\\d+\\.\\d+)|(\\d+))(元)?(可用)");
        Matcher mCoupon1 = pCoupon1.matcher(couponStr);
        while (mCoupon1.find()) {
            System.out.println(mCoupon1.group());
            strs.add(mCoupon1.group());
        }
//        List<Coupon> coupons = new CouponResolver().resolve(couponStr,"taobao");
//        System.out.println(coupons);
    }
}
