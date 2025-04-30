package io.github.chmagicduck.ai.plus.sdk.infrastructure.wechat.dto;

import lombok.Data;

import java.util.List;

@Data
public class WxWebhookTemplateDTO {

    private String msgtype;

    private Markdown markdown = new Markdown();
    private Text text;

    @Data
    public static class Markdown {
        private String content;
    }

    @Data
    public static class Text {
        private String content;
        private List<String> mentioned_mobile_list;
    }

    public static WxWebhookTemplateDTO buildMarkdown(String content) {
        Markdown markdown = new Markdown();
        markdown.setContent(content);

        WxWebhookTemplateDTO dto = new WxWebhookTemplateDTO();
        dto.setMsgtype("markdown");
        dto.setMarkdown(markdown);
        return dto;
    }

    public static WxWebhookTemplateDTO buildText(String content, List<String> mobileList) {
        Text text = new Text();
        text.setContent(content);
        text.setMentioned_mobile_list(mobileList);

        WxWebhookTemplateDTO dto = new WxWebhookTemplateDTO();
        dto.setMsgtype("text");
        dto.setText(text);
        return dto;
    }
}