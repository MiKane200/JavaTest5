package com.hand.api.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.hand.api.service.CustomerService;
import com.hand.domain.entity.Address;
import com.hand.domain.entity.Customer;
import com.hand.domain.entity.Film;
import com.hand.domain.entity.Page;
import com.hand.infra.mapper.AddressMapper;
import com.hand.infra.mapper.CustomerMapper;
import com.hand.infra.mapper.FilmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Film> getFilms(Page page) {
        PageHelper.offsetPage(page.getPage(), page.getPageSize());
        List<Film> resultFilm = filmMapper.selectAll();
        return resultFilm;
    }

    @Override
    public boolean login(String firstName, String lastName) {
        Example example = Example.builder(Customer.class)
                .select("lastName")
                .where(Sqls.custom().andEqualTo("firstName", firstName))
                .build();
        List<Customer> customers = customerMapper.selectByExample(example);

        for (Customer customer : customers) {
            if (customer.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int save(Customer customer, String address) {
        Example example = Example.builder(Address.class)
                .select("addressId")
                .where(Sqls.custom().andEqualTo("address", address))
                .build();
        List<Address> addresses = addressMapper.selectByExample(example);

        if (!address.isEmpty()) {
            for (Address address1 : addresses) {
                customer.setAddressId(address1.getAddressId());
                customer.setActive(true);
                customer.setCreateDate(new Date());
                customer.setLastUpdate(new Date());
                customer.setStoreId((byte) 1);
                customerMapper.insertUser(customer);
                Example example1 = Example.builder(Customer.class)
                        .select("customerId")
                        .build();
                List<Customer> customerList = customerMapper.selectByExample(example1);
                return customerList.get(customerList.size()-1).getCustomerId()+1;
            }
        }
        return -1;
    }

    @Override
    public Customer update(Customer customer) {
        Example example = Example.builder(Customer.class)
                .where(Sqls.custom().andEqualTo("customerId", customer.getCustomerId()))
                .build();
        customer.setAddressId((short) 1);
        customer.setActive(true);
        customer.setCreateDate(new Date());
        customer.setLastUpdate(new Date());
        customer.setStoreId((byte) 1);
        customer.setEmail("123update@hand.com");
        int flag = customerMapper.updateByExample(customer, example);
        return customer;
    }

    @Override
    public boolean delete(String cId) {
        Example example = Example.builder(Customer.class)
                .where(Sqls.custom().andEqualTo("customerId", Short.valueOf(cId)))
                .build();
        int count = customerMapper.deleteByExample(example);
        return (count > 0) ? true : false;
    }

}
