package project.teamauth.configuration.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        int responseStatus = response.getStatus();
        HttpHeaders responseHeaders = response.getHeaderNames().stream()
                .collect(Collectors.toMap(Function.identity(), h -> new ArrayList<>(response.getHeaders(h)),
                        (oldValue, newValue) -> newValue, HttpHeaders::new));
        log.info("-----RESPONSE---------");
        log.info("- Request status:   {}", responseStatus);
        log.info("- Request headers:  {}", responseHeaders);
        log.info("- Request time:     {}", ZonedDateTime.now());
        log.info("----------------------");
    }
}