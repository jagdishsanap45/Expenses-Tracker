package org.gfg.expenseTracker.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.gfg.expenseTracker.model.ExpenseTypes;
import org.gfg.expenseTracker.model.TxnDetails;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTxnRequest {
   @NotNull(message = "userEmail should not be null")
    private String userEmail;

    private String expenseType;

    private Double expenditureCost;

    private LocalDate expenseDate;

    private String expenseNote;

    public TxnDetails toTxnDetails(CreateTxnRequest createTxnRequest){

     return TxnDetails.builder()
             .expenditureAmount(this.expenditureCost)

             .expenseDate(this.expenseDate)
             .expenseNote(this.expenseNote)
             .build();
    }
}
