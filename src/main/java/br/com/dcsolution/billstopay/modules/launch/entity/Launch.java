package br.com.dcsolution.billstopay.modules.launch.entity;

import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchStatusEnum;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "launch")
@Getter
@Setter
@NoArgsConstructor
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "start_position")
    private Integer startPosition;

    @Column(nullable = false, name = "end_position")
    private Integer endPosition;

    @Column(nullable = false, name = "payment_value")
    private BigDecimal paymentValue;

    @Column(nullable = false)
    private LaunchTypeEnum type;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LaunchStatusEnum status;

    @Column(nullable = false, name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "launch_id", referencedColumnName = "id")
    private List<TagLaunch> tags;
}
