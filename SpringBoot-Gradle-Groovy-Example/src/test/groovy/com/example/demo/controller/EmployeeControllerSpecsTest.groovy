package com.example.demo.controller

import com.example.demo.model.Employee
import com.example.demo.model.Employees
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class EmployeeControllerSpecsTest extends Specification{

    @Autowired
    TestRestTemplate restTemplate

    def "get the list of Employees"() {

        when:
        def response = restTemplate.getForEntity('/employees/', Employees)

        then:
        response.statusCode == HttpStatus.OK
        response.body!=null
        println(response.body.employeeList)
    }

    def "Add employee"(){
        given:
        Employee employee = new Employee(id: 4,firstName: "kevin",lastName: "ruth",email: "kevin.ruth@email.com")
        Employees employees = new Employees()
        employees.employeeList.add(employee)

        when:
        def response = restTemplate.postForEntity('/employees/',employee,Object)

        then:
        response.statusCode == HttpStatus.CREATED
    }
}
