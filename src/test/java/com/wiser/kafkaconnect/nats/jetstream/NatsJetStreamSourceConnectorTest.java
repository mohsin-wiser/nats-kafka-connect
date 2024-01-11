package com.wiser.kafkaconnect.nats.jetstream;

import com.wiser.kafkaconnect.nats.jetstream.source.NatsJetStreamSourceConnectorTask;
import com.wiser.kafkaconnect.nats.jetstream.utils.PropertiesUtil;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.wiser.kafkaconnect.nats.jetstream.source.NatsJetStreamSourceConnectorConfig.*;

public class NatsJetStreamSourceConnectorTest {

    @Test
    public void connectorVersionShouldMatch() {
        String version = PropertiesUtil.getConnectorVersion();
        assertEquals(version, new NatsJetStreamSourceConnector().version());
    }

    @Test
    public void checkClassTask() {
        Class<? extends Task> taskClass = new NatsJetStreamSourceConnector().taskClass();
        assertEquals(NatsJetStreamSourceConnectorTask.class, taskClass);
    }

    @Test
    public void checkSpecialCircumstance() {
        final String value = "sameValue";
        assertThrows(ConnectException.class, () -> {
            Map<String, String> props = new HashMap<>();
            props.put(FIRST_NONREQUIRED_PARAM_CONFIG, value);
            props.put(SECOND_NONREQUIRED_PARAM_CONFIG, value);
            new NatsJetStreamSourceConnector().validate(props);
        });
    }

}
