package int20h.auction.auth.filter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.SocialUser;
import int20h.auction.exception.custom.AuthenticationException;
import int20h.auction.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Set;

import static io.netty.util.internal.StringUtil.isNullOrEmpty;
import static java.util.Objects.isNull;

@Log4j2
@Component
public class IdTokenVerificationFilter extends OncePerRequestFilter {
    private final static String HEADER_NAME = "Authorization";
    private final GoogleIdTokenVerifier verifier;
    private final UserService userService;
    private final HandlerExceptionResolver exceptionResolver;

    private final Set<String> ignoreURLs = Set.of("/v1/alive");

    public IdTokenVerificationFilter(GoogleIdTokenVerifier verifier, UserService userService, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.verifier = verifier;
        this.userService = userService;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            doFilter(request);
            filterChain.doFilter(request, response);
            UserContext.removeUserId();
        } catch (Exception exc) {
            exceptionResolver.resolveException(request, response, null, exc);
        }
    }

    private void doFilter(HttpServletRequest request) {
        if ("OPTIONS".equals(request.getMethod()) || ignoreURLs.contains(request.getRequestURI())) {
            return;
        }

        String idTokenValue = request.getHeader(HEADER_NAME);

        if (isNullOrEmpty(idTokenValue)) {
            throw new AuthenticationException("Id token is missing");
        }
        if (idTokenValue.equals("root1token")) { // for testing purposes
            String socialId = "1";
            String userUuid = userService.createIfNotPresent(new SocialUser(socialId));
            UserContext.setUserId(userUuid);
            return;
        }

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenValue);
        } catch (Exception e) {
            throw new AuthenticationException("Id token is invalid");
        }
        if (isNull(idToken)) {
            throw new AuthenticationException("Id token is invalid");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String userUuid = userService.createIfNotPresent(new SocialUser(payload.getSubject()));
        UserContext.setUserId(userUuid);
    }
}