package int20h.auction.service;

import int20h.auction.domain.SocialUser;
import int20h.auction.entitiy.User;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final List<String> usernames = List.of(
            "FrostyPenguin",
            "QuantumJaguar",
            "SapphireRider",
            "ElectricFalcon",
            "GalacticWhale",
            "CelestialSphinx",
            "ThunderHawk",
            "VelvetPhoenix",
            "SolarSorcerer",
            "LunarWanderer"
    );

    public String createIfNotPresent(SocialUser socialUser) {
        Optional<User> optionalUser = userRepository.findBySocialUserId(socialUser.getId());
        if (optionalUser.isPresent()) {
            return optionalUser.get().getId();
        }
        User user = new User();
        user.setSocialUserId(socialUser.getId());
        user.setId(UUID.randomUUID().toString());
        user.setUsername(usernames.get(new Random().nextInt(usernames.size())));

        return userRepository.save(user).getId();
    }
}
