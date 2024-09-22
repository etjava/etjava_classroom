package com.etjava.test;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-04-17  09:53
 * @Description: TODO 优化带有html标签格式的内容
 * @Version: 1.0
 */
public class NoTagHtml {

    public static void main(String[] args) {
        String str = "<script type='text/javascript'>";
        String html = StringEscapeUtils.escapeHtml(str);
        System.out.println(html);
    }
}
