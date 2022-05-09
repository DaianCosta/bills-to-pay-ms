package br.com.dcsolution.billstopay.modules.launch.entity;

import br.com.dcsolution.billstopay.modules.category.entity.Category;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchStatusEnum;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchTypeEnum;
import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "launch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    private LaunchTypeEnum type;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LaunchStatusEnum status;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name = "launch_id", referencedColumnName = "id")
    private List<TagLaunch> tags;
}
