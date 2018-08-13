package com.hand.infra.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Configuration;

@Configuration
@Endpoint(id = "my-endpoint")
public class MyEndpoint {

    @ReadOperation(produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> getEndpoint() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("message", "this is my getEndpoint1");
        return map;
    }

    @WriteOperation
    public Map<String, Object> postEndpoint() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("message", "this is my postEndpoint");
        return map;
    }

    @DeleteOperation
    public Map<String, Object> deleteEndpoint() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("message", "this is my deleteEndpoint");
        return map;
    }

}
