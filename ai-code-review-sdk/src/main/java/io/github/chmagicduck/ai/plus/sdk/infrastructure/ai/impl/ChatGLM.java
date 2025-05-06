package io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.impl;

import com.alibaba.fastjson2.JSON;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.IOpenAI;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.dto.ChatCompletionRequestDTO;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.dto.ChatCompletionSyncResponseDTO;
import io.github.chmagicduck.ai.plus.sdk.utils.BearerTokenUtils;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class ChatGLM implements IOpenAI {

    private final String apiHost;
    private final String apiKeySecret;

    @Override
    public ChatCompletionSyncResponseDTO completions(ChatCompletionRequestDTO requestDTO) throws Exception {
        String token = BearerTokenUtils.getToken(apiKeySecret);

        URL url = new URL(apiHost);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = JSON.toJSONString(requestDTO).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return JSON.parseObject(content.toString(), ChatCompletionSyncResponseDTO.class);
    }
}
