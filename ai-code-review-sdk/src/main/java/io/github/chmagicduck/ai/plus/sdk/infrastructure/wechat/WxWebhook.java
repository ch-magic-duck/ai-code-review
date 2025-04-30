package io.github.chmagicduck.ai.plus.sdk.infrastructure.wechat;

import com.alibaba.fastjson2.JSON;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.wechat.dto.AiReviewTemplate;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.wechat.dto.WxWebhookTemplateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

@Getter
@AllArgsConstructor
public class WxWebhook {

    private final Logger logger = LoggerFactory.getLogger(WxWebhook.class);

    private final String key;

    public void sendReviewMessage(Map<String, Object> data) throws Exception {
        send(new AiReviewTemplate(data).getWxWebHookTemplateDTO());
    }

    public void send(WxWebhookTemplateDTO dto) throws Exception {
        URL url = new URL(String.format("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s", key));

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = JSON.toJSONString(dto).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String response = scanner.useDelimiter("\\A").next();
            logger.info("send wechat webhook response: {}", response);
        }
    }


}
