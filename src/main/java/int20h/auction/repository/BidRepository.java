package int20h.auction.repository;

import int20h.auction.entitiy.Bid;
import int20h.auction.entitiy.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, String> {
    Optional<Bid> findByIsBestAndLot(boolean best, Lot lot);

}
