package int20h.auction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AliveController {

    @GetMapping("/alive")
    public boolean isAlive() {
        return true;
    }

    @GetMapping("/restricted")
    public boolean isRestricted() {
        return true;
    }
}
