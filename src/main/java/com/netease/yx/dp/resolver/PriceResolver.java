package com.netease.yx.dp.resolver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceResolver implements Resolver {
    private static final Logger logger = LoggerFactory.getLogger(PriceResolver.class);

    public List<Double> resolve(String content, String source) {
        List<Double> resultPrices = new ArrayList();
        Document document = Jsoup.parse(content);
        Pattern pPrice = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
        switch (source) {
            case "tmall":
                for (Element ele : document.getElementsByClass("tm-price")) {
                    Matcher m = pPrice.matcher(ele.text());
                    while (m.find()) { //用while 可兼容区间价格的解析
                        resultPrices.add(Double.valueOf(m.group()));
                    }
                }
                logger.info("由 tm-price 解析取得天猫价格为 {}", resultPrices);
                break;
            case "jd":
                for (Element ele : document.getElementsByClass("p-price")) {
                    Matcher m = pPrice.matcher(ele.text());
                    while (m.find()) { //用while 可兼容区间价格的解析
                        resultPrices.add(Double.valueOf(m.group()));
                    }
                }
                logger.info("由 p-price 解析取得京东价格为 {}", resultPrices);
                break;
            case "taobao":
                for (Element ele : document.getElementsByClass("tb-rmb-num")) {
                    Matcher m = pPrice.matcher(ele.text());
                    while (m.find()) { //用while 可兼容区间价格的解析
                        resultPrices.add(Double.valueOf(m.group()));
                    }
                }
                logger.info("由 tb-rmb-num 解析取得淘宝价格为 {}", resultPrices);
                break;
        }
        return resultPrices;
    }
}
