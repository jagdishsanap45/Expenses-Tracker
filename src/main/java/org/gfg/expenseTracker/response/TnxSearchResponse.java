package org.gfg.expenseTracker.response;

import lombok.*;
import org.gfg.expenseTracker.model.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TnxSearchResponse {
    private User user;
    private Double expenditureAmount;
    private String expenseType;
    private String expenseDate;


    public void add(List<TnxSearchResponse> tnxSearchResponses) {

    }
}
