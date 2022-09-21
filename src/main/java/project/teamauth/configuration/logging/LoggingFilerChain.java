package project.teamauth.configuration.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggingFilerChain extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestUrl = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        HttpHeaders requestHeaders = Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), h -> Collections.list(request.getHeaders(h)),
                        (oldValue, newValue) -> newValue, HttpHeaders::new));
        log.info("-----REQUEST----------");
        log.info("- Request url:      {}", requestUrl);
        log.info("- Request method:   {}", requestMethod);
        log.info("- Request headers:  {}", requestHeaders);
        log.info("- Request time:     {}", ZonedDateTime.now());
        chain.doFilter(request, response);
    }
}