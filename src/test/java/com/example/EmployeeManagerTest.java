package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeManagerTest {

    private EmployeeRepository employeeRepositoryMock;
    private BankService bankServiceMock;
    private EmployeeManager employeeManager;

    @BeforeAll
    void setUp() {
        bankServiceMock = mock(BankService.class);
        employeeRepositoryMock = mock(EmployeeRepository.class);
        employeeManager = new EmployeeManager(employeeRepositoryMock, bankServiceMock);
    }

    @Test
    public void testShouldPayTwoEmployeesMockito() {
        Employee employee1 = new Employee("1",10000);
        Employee employee2 = new Employee("2",20000);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeRepositoryMock.findAll()).thenReturn(employeeList);
        int payments = employeeManager.payEmployees();
        assertEquals(2,payments);

        verify(bankServiceMock,times(2)).pay(anyString(),anyDouble());
    }

    @Test
    public void testShouldPayTwoEmployees() {
        EmployeeRepositoryStub employeeRepositoryStub = new EmployeeRepositoryStub();
        BankServiceDummy bankServiceDummy = new BankServiceDummy();
        employeeManager = new EmployeeManager(employeeRepositoryStub, bankServiceDummy);

        int payments = employeeManager.payEmployees();
        assertEquals(2,payments);
    }

}