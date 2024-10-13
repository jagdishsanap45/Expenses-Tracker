package org.gfg.expenseTracker.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.gfg.expenseTracker.model.ExpenseTypes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExpenseTypeRequest {
    @NotBlank(message = "expensetype should not blank")
   String expenseType;
    @NotBlank(message = "useremail should not blank")
    String userEmail;

    public ExpenseTypes toExpenseTypes() {
       return ExpenseTypes.builder()
                .expenseType(this.expenseType)
                .createdBy(this.userEmail)
                .build();
    }
}
