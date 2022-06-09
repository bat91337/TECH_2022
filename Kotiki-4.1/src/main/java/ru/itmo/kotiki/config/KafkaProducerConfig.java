package ru.itmo.kotiki.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import ru.itmo.kotiki.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaProducerId;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        return props;
    }

    @Bean
    public ProducerFactory<Long, KotikDto> producerKotikFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, KotikDto> kafkaKotikTemplate() {
        KafkaTemplate<Long, KotikDto> template = new KafkaTemplate<>(producerKotikFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, UserDto> producerUserFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, UserDto> kafkaUserTemplate() {
        KafkaTemplate<Long, UserDto> template = new KafkaTemplate<>(producerUserFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, OwnerDto> producerOwnerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, OwnerDto> kafkaOwnerTemplate() {
        KafkaTemplate<Long, OwnerDto> template = new KafkaTemplate<>(producerOwnerFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, OwnerKotikDto> producerOwnerKotikFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, OwnerKotikDto> kafkaOwnerKotikTemplate() {
        KafkaTemplate<Long, OwnerKotikDto> template = new KafkaTemplate<>(producerOwnerKotikFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, OwnerUserDto> producerOwnerUserFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, OwnerUserDto> kafkaOwnerUserTemplate() {
        KafkaTemplate<Long, OwnerUserDto> template = new KafkaTemplate<>(producerOwnerUserFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, List<KotikDto>> producerKotikiFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, List<KotikDto>> producerKotikiTemplate() {
        KafkaTemplate<Long, List<KotikDto>> template = new KafkaTemplate<>(producerKotikiFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, List<OwnerDto>> producerOwnersFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, List<OwnerDto>> producerOwnersTemplate() {
        KafkaTemplate<Long, List<OwnerDto>> template = new KafkaTemplate<>(producerOwnersFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, List<UserDto>> producerUsersFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, List<UserDto>> producerUsersTemplate() {
        KafkaTemplate<Long, List<UserDto>> template = new KafkaTemplate<>(producerUsersFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}
