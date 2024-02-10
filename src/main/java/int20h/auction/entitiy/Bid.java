package int20h.auction.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bids")
public class Bid {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @Column(name = "lot_id")
    private Lot lot;
    @Column(name = "price", columnDefinition = "bigint")
    private Long price;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "is_best")
    private boolean isBest;
    @Column(name = "created_at", columnDefinition = "timestamp")
    private Instant createdAt;

}
