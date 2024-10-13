package org.gfg.expenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "txnDetails")
public class TxnDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double expenditureAmount;

    @ManyToOne //1 user can have multiple transaction
    @JoinColumn// becoz of this only user data will go in table
    private User user;

    @ManyToOne
    @JoinColumn
    private  ExpenseTypes expenseTypes;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private  Date updatedAt;

    private LocalDate expenseDate;

    private String expenseNote;

}
