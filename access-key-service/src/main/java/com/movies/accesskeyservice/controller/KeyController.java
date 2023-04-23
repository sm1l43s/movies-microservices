package com.movies.accesskeyservice.controller;

import com.movies.accesskeyservice.service.AccessKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Access Keys", description = "Набор методов для работы с ключами доступа к API Kinopoisk.")
@RestController
@RequestMapping("/api/v1/keys")
@RequiredArgsConstructor
@Slf4j
public class KeyController {

    private final AccessKeyService keyService;

    @Operation(summary = "Позволяет получить ключ доступа к API Kinopoisk",
            description = "Возвращает случайных ключ доступа.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class)
                            )
                    }
            )
    })
    @GetMapping
    public String getAccessKey() {
        log.info("Requesting next access key");
        return keyService.next();
    }
}
