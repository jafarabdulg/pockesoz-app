package com.jafar.pockesoz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jafar.pockesoz.entity.constant.PocketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_pocket")
public class Pocket {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "pocket_id")
    private String id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column
    private Long balance;

    @Enumerated(EnumType.STRING)
    private PocketType type;

    @Column
    private Boolean isActive;
}
