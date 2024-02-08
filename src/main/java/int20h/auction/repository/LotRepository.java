package int20h.auction.repository;

import int20h.auction.entitiy.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, String> {
}
