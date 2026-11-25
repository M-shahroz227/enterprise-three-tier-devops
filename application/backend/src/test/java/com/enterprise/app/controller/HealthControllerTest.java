package com.enterprise.app.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthControllerTest {

    @Test
    void healthShouldReturnSuccessMessage() {
        HealthController controller = new HealthController();

        String response = controller.health();

        assertEquals(
            "Backend is running successfully!",
            response
        );
    }
}
