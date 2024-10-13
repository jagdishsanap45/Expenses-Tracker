package org.gfg.expenseTracker.service;

import org.gfg.expenseTracker.model.*;
import org.gfg.expenseTracker.repository.ExpenseTypeRepository;
import org.gfg.expenseTracker.repository.TxnRepository;
import org.gfg.expenseTracker.repository.UserRepository;
import org.gfg.expenseTracker.request.CreateTxnRequest;
import org.gfg.expenseTracker.response.AnalyticalResponse;
import org.gfg.expenseTracker.response.CreateTxnResponse;
import org.gfg.expenseTracker.response.TnxSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TxnService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TxnRepository txnRepository;

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    public CreateTxnResponse addUserTxn(CreateTxnRequest createTxnRequest) {
            //step 1 -- user if not exist --> email
            //step 2 ---> if not it will be there
            //step 3 --> txn in txndetails

        User userFromDb = userRepository.findByEmailAddressJPQL(createTxnRequest.getUserEmail());

        if (userFromDb == null){
            User user = User.builder()
                    .email(createTxnRequest.getUserEmail())
                    .userStatus(UserStatus.ACTIVE)
                    .build();
            userFromDb = userRepository.save(user);
        }
        ExpenseTypes expenseTypesFromDb = expenseTypeRepository.findByExpenseType(createTxnRequest.getExpenseType());

        if (expenseTypesFromDb == null){
            ExpenseTypes expenseTypes = ExpenseTypes.builder()
//                    .expenseType(CreateTxnRequest.getExpenseType())
                    .createdBy(createTxnRequest.getUserEmail()).build();
            expenseTypesFromDb = expenseTypeRepository.save(expenseTypes);
        }

        TxnDetails txnDetails = createTxnRequest.toTxnDetails(createTxnRequest);
        txnDetails.setUser(userFromDb);
        txnDetails.setExpenseTypes(expenseTypesFromDb);
        TxnDetails txnDetailsFromDb = txnRepository.save(txnDetails);

        return CreateTxnResponse.builder().userId(userFromDb.getId()).expenseId(expenseTypesFromDb.getId()).build();
    }

    public List<TnxSearchResponse> fetchUserTnxDetails(TxnFilterType txnFilterType, TxnFilterOperators operators, String[] values) throws ParseException {
            List<TnxSearchResponse> list = new ArrayList<>();
            List<TxnDetails> txnDetails = new ArrayList<>();

            //first of all i will get id on basis on expense type

        List<ExpenseTypes> expenseIds = expenseTypeRepository.findByExpenseTypeIn(values);
                    //Integer arr[] = expenseIds
            switch (txnFilterType){
                case EXPENSE_TYPE :
                    txnDetails.addAll(txnRepository.findByExpenseTypesId(values));
                    break;
                case EXPENDITURE_AMOUNT:
                    switch (operators) {

                        case EQUALS:
                        txnDetails.addAll(txnRepository.findByExpenditureAmount(Double.valueOf(values[0])));
                        break;

                        case LESS_THAN_EQUALS:
                            txnDetails.addAll(txnRepository.findByExpenditureAmountLessThanEqual(Double.valueOf(values[0])));
                            break;
                    }
                    break;

                case EXPENSE_DATE:
                    switch (operators){
                        case LESS_THAN_EQUALS:
                            txnDetails.addAll(txnRepository.findByExpenseDateLessThanEqual(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(values[0])));
                            break;
                    }
                    break;
            }
            list = convertToSearchResponse(txnDetails);
            return list;
    }
     private List<TnxSearchResponse> convertToSearchResponse(List<TxnDetails> txnDetailsList){
        List<TnxSearchResponse> tnxSearchResponses = new ArrayList<>();

         for (int i = 0; i < txnDetailsList.size(); i++) {
             TnxSearchResponse tnxSearchResponse =TnxSearchResponse.builder()
                     .user(txnDetailsList.get(i).getUser())
                     .expenditureAmount(txnDetailsList.get(i).getExpenditureAmount())
                     .expenseDate((txnDetailsList.get(i).getExpenseDate().toString()))
                     .expenseType(txnDetailsList.get(i).getExpenseTypes().getExpenseType()).build();
                tnxSearchResponse.add(tnxSearchResponses);
             
         }
         return tnxSearchResponses;
     }

    public AnalyticalResponse fetchCalculatedResponse(String email) {

        LocalDate todayDate = LocalDate.now();

        LocalDate sevenDayBackDate = LocalDate.now().minusDays(7);

        User user = userRepository.findByEmail(email);
        Double oneDayAmount = txnRepository.getAggregatedData(todayDate, user.getId());
        Double sevenDayAmount = txnRepository.getAggregatedData(sevenDayBackDate, user.getId());


        return AnalyticalResponse.builder()
                .userEmail(email)
                .oneDayAmount(oneDayAmount)
                .sevenDayAmount(sevenDayAmount).build();











    }
}
