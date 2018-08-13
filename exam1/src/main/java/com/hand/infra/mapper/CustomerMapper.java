package com.hand.infra.mapper;

import com.hand.domain.entity.Customer;
import com.hand.domain.entity.Film;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;

public interface CustomerMapper extends Mapper<Customer> {
    @Insert("insert into customer (store_id,first_name,last_name,email,address_id,active,create_date,last_update) values " +
            "(#{storeId}, #{firstName},#{lastName}, #{email},#{addressId}, #{active},#{createDate},#{lastUpdate})")
    @Options(useGeneratedKeys=true, keyProperty="customer_id", keyColumn="customerId")
    void insertUser(Customer customer);
}