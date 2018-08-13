package com.hand.api.service;

import java.util.List;

import com.hand.domain.entity.Customer;
import com.hand.domain.entity.Film;
import com.hand.domain.entity.Page;
import org.springframework.stereotype.Service;

public interface CustomerService {
    public int save(Customer customer,String address);
    public List<Film> getFilms(Page page);
    public boolean login( String firstName,String lastName);
    public Customer update(Customer customer);
    public boolean delete(String cId);
}
