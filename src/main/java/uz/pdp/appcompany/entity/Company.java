package uz.pdp.appcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String corpName;

    @Column(nullable = false)
    private String directorName;

    @OneToOne
    private Address address;
}