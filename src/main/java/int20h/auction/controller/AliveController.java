package int20h.auction.controller;

import int20h.auction.util.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Path.PREFIX)
public class AliveController {

    @GetMapping(Path.IS_ALIVE)
    public boolean isAlive() {
        return true;
    }

    @GetMapping(Path.IS_RESTRICTED)
    public boolean isRestricted() {
        return true;
    }
}
