package com.jafar.pockesoz.entity;

import com.jafar.pockesoz.entity.constant.TransType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "t_transaction")
public class Transaction {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Long amount;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private TransType type;

    @Column
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "src_pocket_id")
    private Pocket thisPocket;

    @ManyToOne
    @JoinColumn(name = "dest_pocket_id")
    private Pocket otherPocket;
}
