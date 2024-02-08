package int20h.auction.service;

import int20h.auction.domain.SocialUser;
import int20h.auction.entitiy.User;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String createOrUpdate(SocialUser socialUser) {
        User user = userRepository.findBySocialUserId(socialUser.getId()).orElseGet(User::new);

        return userRepository.save(user).getId();
    }
}
