package int20h.auction.auth.context;

import lombok.Data;

@Data
public class UserContext {
    private static ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setUserId(String userId) {
        UserContext.userId.set(userId);
    }

    public static void removeUserId() {
        UserContext.userId.remove();
    }

    public static String getUserId() {
        return UserContext.userId.get();
    }
}