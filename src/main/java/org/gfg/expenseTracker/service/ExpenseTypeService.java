package org.gfg.expenseTracker.service;

import org.gfg.expenseTracker.model.ExpenseTypes;
import org.gfg.expenseTracker.model.User;
import org.gfg.expenseTracker.model.UserStatus;
import org.gfg.expenseTracker.repository.ExpenseTypeRepository;
import org.gfg.expenseTracker.repository.UserRepository;
import org.gfg.expenseTracker.request.CreateExpenseTypeRequest;
import org.gfg.expenseTracker.response.CreateExpenseTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseTypeService {

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;
    //userreposietry is autowired to get users id
    @Autowired
    private UserRepository userRepository;

    public CreateExpenseTypeResponse addExpenseType(CreateExpenseTypeRequest expenseTypeRequest) {
        ExpenseTypes expenseTypesFromDB = expenseTypeRepository.findByExpenseType(expenseTypeRequest.getExpenseType());

      if (expenseTypesFromDB == null) {
          ExpenseTypes expenseTypes = expenseTypeRequest.toExpenseTypes();
          expenseTypesFromDB =expenseTypeRepository.save(expenseTypes);
      }
        User userFromDB = userRepository.findByEmail(expenseTypesFromDB.getCreatedBy()) ;

      if (userFromDB == null){
          User user = User.builder()
                  .email(expenseTypeRequest.getUserEmail())
                  .userStatus(UserStatus.ACTIVE)
                  .build();

          userFromDB = userRepository.save(user);
      }
        CreateExpenseTypeResponse createExpenseTypeResponse = CreateExpenseTypeResponse.builder()
                .expenseId(expenseTypesFromDB.getId())
                .userId(userFromDB.getId())
                .build();

        return createExpenseTypeResponse;
    }
}
