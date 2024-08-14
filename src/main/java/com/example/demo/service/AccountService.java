package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> findById(String username);
}
