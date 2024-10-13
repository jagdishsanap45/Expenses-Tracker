package org.gfg.expenseTracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //long string so sorting and indexind get difficult becoz of UUID

    @Column(length = 30)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 15, unique = true)
    private String contact;


    @CreationTimestamp //To know at  time   user created its profile
    private Date createdAt;

    @UpdateTimestamp //To Know at time user updated its profile
    private  Date updateAt;

    //When user is doing something wrong in application, i want block, Active, Inactive
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TxnDetails> txnDetails;

}
