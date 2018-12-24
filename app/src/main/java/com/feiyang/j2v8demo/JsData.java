package com.feiyang.j2v8demo;

/**
 * Copyright:j2v8Demo
 * Author: liyang <br>
 * Date:2018/12/23 5:47 PM<br>
 * Desc: <br>
 */
public class JsData {
  private   String content;

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JsData(String content) {
        this.content = content;
    }
}
