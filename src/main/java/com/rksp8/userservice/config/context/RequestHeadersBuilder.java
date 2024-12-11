package com.rksp8.userservice.config.context;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;


import java.util.Arrays;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class RequestHeadersBuilder {


    /**
     * Добавление заголовков в запрос
     *
     * @param request запрос
     * @param customHeader заголовок, который нужно включить в запрос
     * @return запрос с добавленными заголовками
     */
    public ServerRequest apply(ServerRequest request, CustomHeader customHeader) {

        try {


            Consumer<HttpHeaders> headers = httpHeaders -> httpHeaders.add(customHeader.val, UsernameContext.getUsername());

            return ServerRequest.from(request)
                    .headers(headers)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}