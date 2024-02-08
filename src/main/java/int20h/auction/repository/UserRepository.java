package int20h.auction.repository;

import int20h.auction.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findBySocialUserId(String socialUserId);
}
