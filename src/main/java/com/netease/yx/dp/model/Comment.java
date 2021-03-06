package com.netease.yx.dp.model;

import java.util.List;

/**
 * @author zhuangfengming@corp.netease.com
 * @since 2021-04-01 11:05
 */
public class Comment {
    private String itemId;

    private String itemName;

    private String itemUrl;

    private String itemBrand;

    private Double itemPrice;

    private Long yxItemId;

    private String commentCounterJson;

    private Integer crawlerCommentCnt;

    private Integer totalCommentCnt;

    private String source;

    private String crawlerData;

    private String goodCommnetStr;

    private String poorCommentStr;

    private String generalCommentStr;

    private String afterCommentStr;

    private List logInfo;

    public Comment() {

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getYxItemId() {
        return yxItemId;
    }

    public void setYxItemId(Long yxItemId) {
        this.yxItemId = yxItemId;
    }

    public String getCommentCounterJson() {
        return commentCounterJson;
    }

    public void setCommentCounterJson(String commentCounterJson) {
        this.commentCounterJson = commentCounterJson;
    }

    public Integer getCrawlerCommentCnt() {
        return crawlerCommentCnt;
    }

    public void setCrawlerCommentCnt(Integer crawlerCommentCnt) {
        this.crawlerCommentCnt = crawlerCommentCnt;
    }

    public Integer getTotalCommentCnt() {
        return totalCommentCnt;
    }

    public void setTotalCommentCnt(Integer totalCommentCnt) {
        this.totalCommentCnt = totalCommentCnt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCrawlerData() {
        return crawlerData;
    }

    public void setCrawlerData(String crawlerData) {
        this.crawlerData = crawlerData;
    }

    public String getGoodCommnetStr() {
        return goodCommnetStr;
    }

    public void setGoodCommnetStr(String goodCommnetStr) {
        this.goodCommnetStr = goodCommnetStr;
    }

    public String getPoorCommentStr() {
        return poorCommentStr;
    }

    public void setPoorCommentStr(String poorCommentStr) {
        this.poorCommentStr = poorCommentStr;
    }

    public String getGeneralCommentStr() {
        return generalCommentStr;
    }

    public void setGeneralCommentStr(String generalCommentStr) {
        this.generalCommentStr = generalCommentStr;
    }

    public String getAfterCommentStr() {
        return afterCommentStr;
    }

    public void setAfterCommentStr(String afterCommentStr) {
        this.afterCommentStr = afterCommentStr;
    }

    public List getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(List logInfo) {
        this.logInfo = logInfo;
    }
}
