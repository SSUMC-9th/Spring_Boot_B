package com.ssu.umc9th2.spring_boot_b.common.log.discord;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DiscordAlarmSender {

    private final Environment environment;
    private final DiscordUtil discordUtil;

    @Value("${logging.discord.web-hook-url}")
    private String webHookUrl;

    private final WebClient webClient = WebClient.create();

    public void sendDiscordAlarm(Exception exception, HttpServletRequest request) {
        List<String> alarmingProfiles = List.of("local");

        boolean isDevProfile = Arrays.stream(environment.getActiveProfiles())
                .anyMatch(alarmingProfiles::contains);

        if (!isDevProfile) return;

        Object alarmBody = discordUtil.createMessage(exception, request);

        webClient.post()
                .uri(webHookUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(alarmBody)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(
                        unused -> log.info("[*] Success to Send Discord Alarm"),
                        error -> log.error("[*] Error to Send Discord Alarm | Exception : {}", exception.getClass().getSimpleName(), error)
                );
    }
}
