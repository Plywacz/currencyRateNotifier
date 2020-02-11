package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

import javax.persistence.*;

@MappedSuperclass
public class BasicEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
