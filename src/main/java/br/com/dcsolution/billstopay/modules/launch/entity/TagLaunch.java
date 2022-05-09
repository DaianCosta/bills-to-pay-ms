package br.com.dcsolution.billstopay.modules.launch.entity;

import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tag_launch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagLaunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "launch_id", referencedColumnName = "id", nullable = false)
    private Launch launch;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false)
    private Tag tag;
}
