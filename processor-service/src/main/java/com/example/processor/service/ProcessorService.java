
package com.example.processor.service;

import com.example.processor.model.ProcessedData;
import com.example.processor.repository.ProcessedDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProcessorService {

    private final ProcessedDataRepository repository;

    // TODO extract all hardcoded values into .properties files
    @KafkaListener(topics = "example-topic", groupId = "processor-group")
    public void processMessage(String message) {
        ProcessedData data = new ProcessedData().setMessage(message);

        // TODO other operation that makes sense
        repository.save(data);
        System.out.println("Processed and saved message: " + message);
    }
}
