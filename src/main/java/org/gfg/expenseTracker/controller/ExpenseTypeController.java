package org.gfg.expenseTracker.controller;

import jakarta.validation.Valid;
import org.gfg.expenseTracker.request.CreateExpenseTypeRequest;
import org.gfg.expenseTracker.response.CreateExpenseTypeResponse;
import org.gfg.expenseTracker.response.CreateUserResponse;
import org.gfg.expenseTracker.response.GenericResponse;
import org.gfg.expenseTracker.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenseTracker")
public class ExpenseTypeController {
   @Autowired
    private ExpenseTypeService expenseTypeService;


    @PostMapping("/addExpenseType")
    public GenericResponse<CreateExpenseTypeResponse> addExpenseType(@Valid @RequestBody CreateExpenseTypeRequest expenseTypeRequest){
        CreateExpenseTypeResponse createExpenseTypeResponse = expenseTypeService.addExpenseType(expenseTypeRequest);
        GenericResponse<CreateExpenseTypeResponse> genericResponse = GenericResponse.<CreateExpenseTypeResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Expense Type Saved")
                .statusCode(0)
                .data(createExpenseTypeResponse)
                .build();
        return genericResponse;
    }





   }

