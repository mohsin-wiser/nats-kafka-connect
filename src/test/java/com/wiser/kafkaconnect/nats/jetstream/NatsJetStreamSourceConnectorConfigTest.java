package com.wiser.kafkaconnect.nats.jetstream;

import com.wiser.kafkaconnect.nats.jetstream.source.NatsJetStreamSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.wiser.kafkaconnect.nats.jetstream.source.NatsJetStreamSourceConnectorConfig.*;

public class NatsJetStreamSourceConnectorConfigTest {

    @Test
    public void basicParamsAreMandatory() {
        assertThrows(ConfigException.class, () -> {
            Map<String, String> props = new HashMap<>();
            new NatsJetStreamSourceConnectorConfig(props);
        });
    }

    public void checkingNonRequiredDefaults() {
        Map<String, String> props = new HashMap<>();
        NatsJetStreamSourceConnectorConfig config = new NatsJetStreamSourceConnectorConfig(props);
        assertEquals("foo", config.getString(FIRST_NONREQUIRED_PARAM_CONFIG));
        assertEquals("bar", config.getString(SECOND_NONREQUIRED_PARAM_CONFIG));
    }

}
