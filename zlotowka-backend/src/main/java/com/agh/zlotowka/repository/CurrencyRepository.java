package com.agh.zlotowka.repository;

import com.agh.zlotowka.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
