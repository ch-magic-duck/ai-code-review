package io.github.chmagicduck.ai.plus.sdk;


import io.github.chmagicduck.ai.plus.sdk.domain.service.IOpenAiCodeReviewService;
import io.github.chmagicduck.ai.plus.sdk.domain.service.impl.ChatGLMCodeReviewService;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.IOpenAI;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.ai.impl.ChatGLM;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.git.GitCommand;
import io.github.chmagicduck.ai.plus.sdk.infrastructure.wechat.WxWebhook;
import io.github.chmagicduck.ai.plus.sdk.utils.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AiCodeReview {

    private static final Logger logger = LoggerFactory.getLogger(AiCodeReview.class);

    public static void main(String[] args) {
        GitCommand gitCommand = new GitCommand(
                Env.get("GITHUB_REVIEW_LOG_URI"),
                Env.get("GITHUB_TOKEN"),
                Env.get("COMMIT_PROJECT"),
                Env.get("COMMIT_BRANCH"),
                Env.get("COMMIT_AUTHOR"),
                Env.get("COMMIT_MESSAGE")
        );

        WxWebhook wxWebhook = new WxWebhook(
                Env.get("WECHAT_WEBHOOK_KEY")
        );

        IOpenAI openAI = new ChatGLM(
                Env.get("CHATGLM_APIHOST"),
                Env.get("CHATGLM_APIKEYSECRET")
        );

        IOpenAiCodeReviewService openAiCodeReviewService = new ChatGLMCodeReviewService(gitCommand, openAI, wxWebhook);
        openAiCodeReviewService.exec();

        logger.info("ai code review done!");
    }
}
