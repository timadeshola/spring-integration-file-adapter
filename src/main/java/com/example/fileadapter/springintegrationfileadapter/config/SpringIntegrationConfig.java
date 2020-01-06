package com.example.fileadapter.springintegrationfileadapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class SpringIntegrationConfig {

    private final String SOURCE = "C:\\Users\\john.adeshola\\Downloads\\email";
    private final String DEST = "C:\\Users\\john.adeshola\\Downloads\\dest";

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "5000"))
    public FileReadingMessageSource fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(SOURCE));
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileInputChannel")
    public FileWritingMessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler writter = new FileWritingMessageHandler(new File(DEST));
        writter.setAutoCreateDirectory(true);
        writter.setExpectReply(false);
        return writter;
    }

}
