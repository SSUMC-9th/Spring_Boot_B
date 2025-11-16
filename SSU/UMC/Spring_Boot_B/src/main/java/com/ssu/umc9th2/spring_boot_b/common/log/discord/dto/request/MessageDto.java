package com.ssu.umc9th2.spring_boot_b.common.log.discord.dto.request;

import java.util.List;

public record MessageDto(
        String content,
        List<EmbedDto> embeds
) {
}
