package org.gfg.expenseTracker.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTxnResponse {
    private Integer userId;
    private Integer expenseId;
}
