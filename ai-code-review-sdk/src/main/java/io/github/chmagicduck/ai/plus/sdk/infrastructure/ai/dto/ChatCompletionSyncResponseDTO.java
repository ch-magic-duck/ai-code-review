package io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.dto;


import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionSyncResponseDTO {

    private List<Choice> choices;

    @Data
    public static class Choice {
        private Message message;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}
