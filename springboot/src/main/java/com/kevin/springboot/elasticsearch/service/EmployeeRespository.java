package com.kevin.springboot.elasticsearch.service;

import com.kevin.springboot.elasticsearch.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/11
 * Time: 16:12
 */
@Component
public interface EmployeeRespository extends ElasticsearchRepository<Employee, String> {
    Employee queryEmployeeById(String id);

}




