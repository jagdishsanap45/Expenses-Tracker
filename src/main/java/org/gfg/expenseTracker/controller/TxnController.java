package org.gfg.expenseTracker.controller;

import jakarta.validation.Valid;
import org.gfg.expenseTracker.model.TxnFilterOperators;
import org.gfg.expenseTracker.model.TxnFilterType;
import org.gfg.expenseTracker.request.CreateTxnRequest;
import org.gfg.expenseTracker.response.*;
import org.gfg.expenseTracker.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/expenseTracker")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/addUserTxn")
    public GenericResponse<CreateTxnResponse>addUserTxn(@Valid @RequestBody CreateTxnRequest createTxnRequest){
        CreateTxnResponse createTxnResponse = txnService.addUserTxn(createTxnRequest);
        GenericResponse<CreateTxnResponse> genericResponse = GenericResponse.<CreateTxnResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Expense Type Saved")
                .statusCode(0)
                .data(createTxnResponse)
                .build();
        return genericResponse;
    }

    //To apply filter for data if user want filter data by sorting, name wise
    // , expense type wise , date wise and etc

    @GetMapping("/fetchTxn")
    public GenericResponse<List<TnxSearchResponse>> fetchUserTnxDetails(@RequestParam("filter")TxnFilterType txnFilterType,
                                                                        @RequestParam("operator")TxnFilterOperators operators,
                                                                        @RequestParam("value") String value) throws ParseException {
            //Create array for comma seprated filter

        String[] values = value.split(",");
        List<TnxSearchResponse> listOfTnxSearchResp = txnService.fetchUserTnxDetails(txnFilterType, operators, values);
        GenericResponse<List<TnxSearchResponse>> genericResponse = GenericResponse.<List<TnxSearchResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Expense Type Saved")
                .statusCode(0)
                .data(listOfTnxSearchResp)
                .build();
        return genericResponse;
    }
    @GetMapping("/fetchCalculatedResults")
    public GenericResponse<AnalyticalResponse> fetchCalculatedResponse(@RequestParam("email") String email){
        AnalyticalResponse analyticalResponse = txnService.fetchCalculatedResponse(email);
        GenericResponse<AnalyticalResponse> genericResponse = GenericResponse.<AnalyticalResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Expense Type Saved")
                .statusCode(0)
                .data(analyticalResponse)
                .build();
        return genericResponse;
    }
}
