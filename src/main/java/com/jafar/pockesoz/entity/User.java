package com.jafar.pockesoz.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_user")
public class User {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "user_id")
    private String id;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private Boolean isActive;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Pocket> pockets;

    public List<Pocket> getPockets() {
        return Collections.unmodifiableList(pockets);
    }

    public void addPocket(Pocket pocket) {
        pockets.add(pocket);
    }

}
