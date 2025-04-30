package io.github.chmagicduck.ai.plus.sdk.domain.model;

import java.util.List;

public class WXWebhook {

    private String msgtype;

    private Markdown markdown = new Markdown();
    private Text text;

    public static WXWebhook buildMarkdown(String content) {
        Markdown markdown = new Markdown();
        markdown.setContent(content);

        WXWebhook wxWebhook = new WXWebhook();
        wxWebhook.setMsgtype("markdown");
        wxWebhook.setMarkdown(markdown);
        return wxWebhook;
    }

    public static WXWebhook buildText(String content, List<String> mobileList) {
        Text text = new Text();
        text.setContent(content);
        text.setMentioned_mobile_list(mobileList);

        WXWebhook wxWebhook = new WXWebhook();
        wxWebhook.setMsgtype("text");
        wxWebhook.setText(text);
        return wxWebhook;
    }


    public static class Markdown {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Text {
        private String content;
        private List<String> mentioned_mobile_list;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMentioned_mobile_list() {
            return mentioned_mobile_list;
        }

        public void setMentioned_mobile_list(List<String> mentioned_mobile_list) {
            this.mentioned_mobile_list = mentioned_mobile_list;
        }
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
