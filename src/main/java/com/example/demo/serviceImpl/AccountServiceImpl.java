package com.example.demo.serviceImpl;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.service.AccountService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO adao;


    @Override
    public Optional<Account> findById(String username) {
        return adao.findById(username);
    }
}
