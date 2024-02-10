package int20h.auction.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

// todo add photo

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lots")
public class Lot {
    @Id
    private String id;
    @Column(name = "title", columnDefinition = "varchar(255)")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price", columnDefinition = "bigint")
    private Long price;
    @Column(name = "close_time", columnDefinition = "timestamp")
    private Instant closeTime;
    @Column(name = "image", columnDefinition = "text")
    private String image;
    @Column(name = "status", columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private LotStatus status;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Column(name = "created_at", columnDefinition = "timestamp")
    private Instant createdAt;

    public enum LotStatus {
        OPEN, CLOSED, CANCELED
    }
}

