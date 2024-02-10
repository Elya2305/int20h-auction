package int20h.auction.util;

public class Path {

    private Path() {
    }

    public static final String PREFIX = "/v1";

    public static final String LOT_MAPPING = PREFIX + "/lot";

    public static final String BID_MAPPING = PREFIX + "/bid";

    public static final String GET_LOT = "/{id}";

    public static final String CANCEL_LOT = "{id}/cancel";

    public static final String UPDATE_LOT = "/{id}";

    public static final String IS_ALIVE = "/alive";

    public static final String IS_RESTRICTED = "/restricted";

}
