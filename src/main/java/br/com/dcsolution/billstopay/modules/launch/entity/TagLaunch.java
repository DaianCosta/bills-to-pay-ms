package br.com.dcsolution.billstopay.modules.launch.entity;

import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "launch_tag")
@Data
@NoArgsConstructor
public class TagLaunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "launch_id")
    private Launch launch;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
