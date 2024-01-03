package net.sorted.fileextractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.tests.annotations.Event;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Disabled
public class InvokeTest {

    private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);


    @ParameterizedTest
    @Event(value = "src/test/resources/s3-notification.json", type = S3Event.class)
    void testS3(S3Event event) {
        logger.info("Invoke TEST - S3");
        Context context = new TestContext();
        Handler handler = new Handler();
        String response = handler.handleRequest(event, context);
        assertEquals("FILE_UPLOAD_BUCKET/inbound/file00001.txt", response);
    }
}
