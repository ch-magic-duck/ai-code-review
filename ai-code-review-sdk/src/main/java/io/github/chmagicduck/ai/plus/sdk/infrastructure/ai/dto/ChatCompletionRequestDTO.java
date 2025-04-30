package io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.dto;

import io.github.chmagicduck.ai.plus.sdk.domain.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionRequestDTO {

    private String model = Model.GLM_4_FLASH.getCode();
    private List<Prompt> messages;

    @Data
    @AllArgsConstructor
    public static class Prompt {
        private String role;
        private String content;
    }
}
