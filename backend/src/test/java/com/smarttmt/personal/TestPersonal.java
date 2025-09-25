package com.smarttmt.personal;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestPersonal {

    @Autowired
    PersonalIService personalIService;

    @DisplayName("TestPersonal test record personal")
    @Test
    void testPersonalRecord() {
        PersonalRecord pr = new PersonalRecord("022", "23232323", "Leo", "Paz", "3000000", "xxxxx@gmail.com");
        assertEquals("Leo", pr.persnomb());
    }

    @DisplayName("TestPersonal test record personal disapara validacion perscodi")
    @Test
    void testValidadorCamposPerscodiNotNull() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        PersonalRecord pr = new PersonalRecord("sasasas", "23232323", "Leo", "Paz", "3000000", "xxxxx@gmail.com");
        Set<ConstraintViolation<PersonalRecord>> violations = validator.validate(pr);

        // Verificar si hay violaciones de las restricciones
        assertTrue(violations.isEmpty());
    }

    @DisplayName("TestPersonal test record personal disapara validacion perscodi empty")
    @Test
    void testValidadorCamposPerscodiEmpty() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        PersonalRecord pr = new PersonalRecord("", "23232323", "Leo", "Paz", "3000000", "xxxxx@gmail.com");
        Set<ConstraintViolation<PersonalRecord>> violations = validator.validate(pr);

        // Verificar si hay violaciones de las restricciones
        assertTrue(!violations.isEmpty());

        // Verificar las violaciones específicas
        for (ConstraintViolation<PersonalRecord> violation : violations) {

            String propertyPath = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();

            if (propertyPath.equals("perscodi")) {
                assertEquals("Campo perscodi no puede estar vacio", errorMessage);
            }
        }

    }

    @Test
    void testGetPersonalPorUsuario() throws Exception {

        PersonalEntity personal = personalIService.getPersonalPorUsuario("C_ALCJAM001");

        assertNotNull(personal);
        assertEquals(personal.getPerscodi(), "2013");

    }
}
