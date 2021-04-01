package com.netease.yx.dp.udf;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netease.yx.dp.model.Comment;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @author zhuangfengming@corp.netease.com
 * @since 2021-04-01 11:12
 */
public class OutsideCommentParser extends UDF {
    public String evaluate(String crawlerData, String platform) {
        JsonParser jsonParser = new JsonParser();
        JsonObject obj = (JsonObject) jsonParser.parse(crawlerData);
        Comment comment = new Comment();

        //item_id
        String itemId = obj.get("theid").getAsString();
        comment.setItemId(itemId);

        //item_name
        String itemName = obj.get("name").getAsString();
        comment.setItemName(itemName);
        Gson gson = new Gson();

        //item_brand
        String itemBrand = obj.get("attributes").getAsString();

        return gson.toJson(comment);
    }

}
