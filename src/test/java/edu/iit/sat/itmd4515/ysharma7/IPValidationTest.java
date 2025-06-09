package edu.iit.sat.itmd4515.ysharma7;

import edu.iit.sat.itmd4515.ysharma7.domain.*;
import jakarta.validation.*;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * JUnit test for validating InsurancePolicy constraints like dates and amounts.  
 * @author yashica
 */
public class IPValidationTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        factory.close();
    }

   @Test
    void testValidPolicy() {
        InsurancePolicy policy = new InsurancePolicy();
        policy.setCustomer(new Customer("SRK", "srk@example.com"));
        policy.setCoverageAmount(100000.0);
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(LocalDate.now().plusYears(1));
        policy.setPolicyType(PolicyType.LIFE);

        Set<ConstraintViolation<InsurancePolicy>> violations = validator.validate(policy);
        assertTrue(violations.isEmpty(), "Valid policy should have no violations");
    }

    @Test
    void testInvalidPolicyNoCustomer() {
        InsurancePolicy policy = new InsurancePolicy();
        policy.setCoverageAmount(100000.0);
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(LocalDate.now().plusYears(1));
        policy.setPolicyType(PolicyType.LIFE);

        Set<ConstraintViolation<InsurancePolicy>> violations = validator.validate(policy);
        assertFalse(violations.isEmpty(), "Policy without customer should violate constraints");
    }

    @Test
    void testNegativeCoverageAmount() {
        InsurancePolicy policy = new InsurancePolicy();
        policy.setCustomer(new Customer("kirti", "kirti@example.com"));
        policy.setCoverageAmount(-50000.0);
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(LocalDate.now().plusYears(1));
        policy.setPolicyType(PolicyType.HEALTH);

        Set<ConstraintViolation<InsurancePolicy>> violations = validator.validate(policy);
        assertFalse(violations.isEmpty(), "Negative coverage amount should violate constraints");
    }
}