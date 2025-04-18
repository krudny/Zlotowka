package com.agh.zlotowka.service;

import com.agh.zlotowka.dto.OneTimeTransactionRequest;
import com.agh.zlotowka.exception.CurrencyConversionException;
import com.agh.zlotowka.model.Currency;
import com.agh.zlotowka.model.OneTimeTransaction;
import com.agh.zlotowka.model.User;
import com.agh.zlotowka.repository.CurrencyRepository;
import com.agh.zlotowka.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public User createUser() {
        Currency currency1 = Currency.builder()
                .isoCode("PLN")
                .build();

        Currency currency2 = Currency.builder()
                .isoCode("USD")
                .build();

        Currency currency3 = Currency.builder()
                .isoCode("EUR")
                .build();
        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
        currencyRepository.save(currency3);

        User user1 = User.builder()
                .firstName("Kamilek")
                .lastName("Rudy")
                .email("kamilek.pl")
                .currency(currency1)
                .currentBudget(new BigDecimal(1000))
                .darkMode(false)
                .build();

        User user2 = User.builder()
                .firstName("Jan")
                .lastName("Nowak")
                .email("gmail.com")
                .currency(currency2)
                .currentBudget(new BigDecimal(2000))
                .darkMode(false)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    public void removeTransactionAmountFromBudget(int currencyId, BigDecimal amount, boolean isIncome, User user) {
        BigDecimal budget = user.getCurrentBudget();
        BigDecimal amountInUserCurrency;

        Currency requestCurrency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new EntityNotFoundException("Currency service: Currency not found with ID: " + currencyId));

        try {
            if (user.getCurrency().getCurrencyId().equals(currencyId)) {
                amountInUserCurrency = amount;
            } else {
                amountInUserCurrency = currencyService.convertCurrency(amount, requestCurrency.getIsoCode(), user.getCurrency().getIsoCode());
            }
            addTransactionToBudget(user, budget, amountInUserCurrency, !isIncome);
        } catch (CurrencyConversionException e) {
            log.error("Currency conversion failed", e);
        } catch (Exception e) {
            log.error("Unexpected Exception in CurrencyService", e);
        }
    }

    public void addTransactionAmountToBudget(int currencyId, BigDecimal amount, boolean isIncome, User user) {
        BigDecimal budget = user.getCurrentBudget();
        BigDecimal amountInUserCurrency;

        Currency requestCurrency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new EntityNotFoundException("Currency service: Currency not found with ID: " + currencyId));

        try {
            if (user.getCurrency().equals(requestCurrency)) {
                amountInUserCurrency = amount;
            } else {
                amountInUserCurrency = currencyService.convertCurrency(amount, requestCurrency.getIsoCode(), user.getCurrency().getIsoCode());
            }
            addTransactionToBudget(user, budget, amountInUserCurrency, isIncome);
        } catch (CurrencyConversionException e) {
            log.error("Currency conversion failed", e);
        } catch (Exception e) {
            log.error("Unexpected Exception in CurrencyService", e);
        }
    }

    private void addTransactionToBudget(User user, BigDecimal budget, BigDecimal amount, boolean isAddTransaction) {
        if (isAddTransaction) {
            user.setCurrentBudget(budget.add(amount));
        } else {
            user.setCurrentBudget(budget.subtract(amount));
        }
        userRepository.save(user);
    }
}
