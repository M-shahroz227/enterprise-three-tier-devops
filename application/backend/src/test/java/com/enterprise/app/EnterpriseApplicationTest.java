package com.enterprise.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;

class EnterpriseApplicationTest {

    @Test
    void constructorShouldCreateApplicationInstance() {
        EnterpriseApplication application = new EnterpriseApplication();

        org.junit.jupiter.api.Assertions.assertNotNull(application);
    }

    @Test
    void mainShouldRunSpringApplication() {
        try (var springApplication = mockStatic(SpringApplication.class)) {
            EnterpriseApplication.main(new String[]{});

            springApplication.verify(
                    () -> SpringApplication.run(
                            EnterpriseApplication.class,
                            new String[]{}
                    )
            );
        }
    }
}
