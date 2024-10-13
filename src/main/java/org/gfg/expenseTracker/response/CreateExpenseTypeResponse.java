package org.gfg.expenseTracker.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExpenseTypeResponse {

    Integer userId;

    Integer expenseId;
}
