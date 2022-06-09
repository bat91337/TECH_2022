package ru.itmo.kotiki.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.dto.*;

import java.util.List;

@Component
public class KafkaTemplateTool {
    @Autowired
    public KafkaTemplate<Long, UserDto> kafkaUserTemplate;

    @Autowired
    public KafkaTemplate<Long, List<UserDto>> kafkaUsersTemplate;

    @Autowired
    public KafkaTemplate<Long, KotikDto> kafkaKotikTemplate;

    @Autowired
    public KafkaTemplate<Long, List<KotikDto>> kafkaKotikiTemplate;

    @Autowired
    public KafkaTemplate<Long, OwnerDto> kafkaOwnerTemplate;

    @Autowired
    public KafkaTemplate<Long, List<OwnerDto>> kafkaOwnersTemplate;

    @Autowired
    public KafkaTemplate<Long, OwnerUserDto> kafkaOwnerUserKafkaTemplate;

    @Autowired
    public KafkaTemplate<Long, OwnerKotikDto> kafkaOwnerKotikTemplate;
}
