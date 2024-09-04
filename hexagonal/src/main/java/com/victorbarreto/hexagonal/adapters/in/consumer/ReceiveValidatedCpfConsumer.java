package com.victorbarreto.hexagonal.adapters.in.consumer;

import com.victorbarreto.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import com.victorbarreto.hexagonal.adapters.in.consumer.message.CustomerMessage;
import com.victorbarreto.hexagonal.application.ports.in.UpdateCustomerInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Component that listens to Kafka messages from the "tp-cpf-validated" topic
 * and processes them to update customer information.
 */
@Component
@Slf4j
public class ReceiveValidatedCpfConsumer {

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    /**
     * Method that receives messages from the "tp-cpf-validated" Kafka topic.
     * The received message is mapped and used to update customer information.
     *
     * @param customerMessage the message received from Kafka containing customer information
     */
    @KafkaListener(topics = "tp-cpf-validated", groupId = "tutorial")
    public void receive(CustomerMessage customerMessage) {
        log.info("Received message from topic 'tp-cpf-validated': {}", customerMessage);

        try {
            var customer = customerMessageMapper.toCustomer(customerMessage);

            updateCustomerInputPort.update(customer, customerMessage.getZipCode());
            log.info("Successfully updated customer with ID: {}", customer.getId());
        } catch (Exception e) {
            log.error("Error processing message: {}", customerMessage, e);
        }

    }

}
