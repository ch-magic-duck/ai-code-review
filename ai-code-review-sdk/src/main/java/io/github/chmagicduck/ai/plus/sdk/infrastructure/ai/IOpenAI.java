package io.github.chmagicduck.ai.plus.sdk.infrastructure.ai;

import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.dto.ChatCompletionRequestDTO;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.dto.ChatCompletionSyncResponseDTO;

public interface IOpenAI {

    ChatCompletionSyncResponseDTO completions(ChatCompletionRequestDTO requestDTO) throws Exception;

}
