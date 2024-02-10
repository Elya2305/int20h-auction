package int20h.auction.repository;

import int20h.auction.entitiy.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface BidRepository extends JpaRepository<Bid, String> {
//    @Query("SELECT * FROM Bid b WHERE b.lot.id = :lotId")
    Long getBestBidByLotId(@Param("lotId") String lotId);
}
