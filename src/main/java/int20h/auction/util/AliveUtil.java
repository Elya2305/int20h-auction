package int20h.auction.util;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AliveUtil {
    private final WebClient webClient;

    /**
     * Free render server shuts down instance if it's not called
     */
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void aliveScheduler() {
        webClient.get()
                .uri("/v1/alive")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
