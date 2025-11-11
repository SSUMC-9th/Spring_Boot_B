package com.ssu.umc9th2.spring_boot_b.common.log.discord;

import com.ssu.umc9th2.spring_boot_b.common.log.discord.dto.request.EmbedDto;
import com.ssu.umc9th2.spring_boot_b.common.log.discord.dto.request.MessageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DiscordUtil {

    public MessageDto createMessage(Exception exception, HttpServletRequest request) {
        String errorTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초"));

        String endpoint = getEndPoint(request);
        String client = getClient(request);
        String stackTrace = getStackTrace(exception);

        String description = "### 에러 발생 시간\n" +
                errorTime +
                "\n### 요청 엔드포인트\n" +
                endpoint +
                "\n### 요청 클라이언트\n" +
                client +
                "\n### 에러 스택 트레이스\n" +
                "```\n" +
                truncateStackTrace(stackTrace) +
                "\n```";

        EmbedDto embed = new EmbedDto("에러 정보", description);
        return new MessageDto("# 🚨 서버 에러 발생 🚨", List.of(embed));
    }

    private String getEndPoint(HttpServletRequest request) {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        return "[" + method + "] " + url;
    }

    private String getClient(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String userIdentifier = "";

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            userIdentifier = " / [UserId]: " + request.getUserPrincipal().getName();
        }

        return "[IP]: " + ip + userIdentifier;
    }

    private String getStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append("\n");
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    private String truncateStackTrace(String stackTrace) {
        return stackTrace.length() > 1000 ? stackTrace.substring(0, 1000) : stackTrace;
    }

}
