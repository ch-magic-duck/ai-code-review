package io.github.chmagicduck.ai.plus.sdk.infrastructure.wechat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class AiReviewTemplate {

    @Getter
    private final WxWebhookTemplateDTO wxWebHookTemplateDTO;

    private String template = "### ⚠️ 您有一份代码审核结果请查收 ⚠️\n" +
            "#### 项目名称：<font color=\"info\">${repo_name}</font>\n" +
            "#### 分支名称：<font color=\"info\">${branch_name}</font>\n" +
            "#### 提交者：<font color=\"info\">${commit_author}</font>\n" +
            "#### 提交信息：<font color=\"info\">${commit_message}</font>\n" +
            "#### 审核结果：<font color=\"comment\">${review_log_url}</font>\n";

    @Getter
    @AllArgsConstructor
    public enum Key {
        REPO_NAME("repo_name", "项目名称"),
        BRANCH_NAME("branch_name", "分支名称"),
        COMMIT_AUTHOR("commit_author", "提交者"),
        COMMIT_MESSAGE("commit_message", "提交信息"),
        REVIEW_LOG_URL("review_log_url", "审核结果链接"),
        ;

        private final String code;
        private final String desc;

        private static final Map<String, Key> map = Arrays.stream(Key.values()).collect(Collectors.toMap(Key::getCode, key -> key));

        public static Key getByCode(String code) {
            return map.get(code);
        }
    }

    public AiReviewTemplate(Map<String, Object> data) {
        data.forEach((key, value) -> {
            Key templateKey = Key.getByCode(key);
            template = template.replace("${" + templateKey.getCode() + "}", value.toString());
        });
        wxWebHookTemplateDTO = WxWebhookTemplateDTO.buildMarkdown(template);
    }
}
