package com.example.hubpay.wallet.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreditWalletControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("GIVEN valid credit transaction data WHEN call credit wallet api THEN success response is returned")
    void creditTransactionSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/credit-wallet/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "walletId": "f9d5b369-89a4-4383-9957-a7bd98236c3f",
                                    "customerId": "77590ca1-6c7a-4819-8e82-aab1c5386536",
                                    "amount": "500",
                                    "currency": "EUR",
                                    "description": "test transaction"
                                }
                                """)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.transactionId").isNotEmpty());
    }

    @Test
    @DisplayName("GIVEN invalid customer id WHEN call credit wallet api THEN customer not found error is returned")
    void creditTransactionCustomerNotFoundErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/credit-wallet/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "walletId": "f9d5b369-89a4-4383-9957-a7bd98236c3f",
                                    "customerId": "77590ca1-6c7a-4819-8e82-aab1c5386535",
                                    "amount": "500",
                                    "currency": "EUR",
                                    "description": "test transaction"
                                }
                                """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "errors": [
                                {
                                    "code": "400 BAD_REQUEST",
                                    "message": "Customer not found for id: 77590ca1-6c7a-4819-8e82-aab1c5386535"
                                }
                            ]
                        }
                        """, true));
    }

    //TODO: test other validations
}
