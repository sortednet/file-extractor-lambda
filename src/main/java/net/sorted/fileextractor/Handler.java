package net.sorted.fileextractor;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Handler implements RequestHandler<S3Event, String>{

    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();

    @Override
    public String handleRequest(S3Event event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        S3EventNotificationRecord record = event.getRecords().get(0);
        String srcBucket = record.getS3().getBucket().getName();
        // Object key may have spaces or unicode non-ASCII characters.
        String srcKey = record.getS3().getObject().getUrlDecodedKey();
        logger.log("File Extractor 0.0.8\n");
        logger.log("RECORD: " + record + "\n");
        logger.log("SOURCE BUCKET: " + srcBucket + "\n");
        logger.log("SOURCE KEY: " + srcKey + "\n");

        logger.log("EVENT TYPE: " + event.getClass() + "\n");

        try {
            String content = processFile(srcBucket, srcKey);
            logger.log("Bytes read: " + content.length() + "\n");
            logger.log("READ FILE: " + content);
        } catch (Throwable e) {
            logger.log("Error " + e.getMessage() + " \n");
        }

        return srcBucket + "/" + srcKey;
    }

    private String processFile(String bucketName, String keyName) throws Exception {

        S3Object o = s3.getObject(bucketName, keyName);
        S3ObjectInputStream s3is = o.getObjectContent();
        ByteArrayOutputStream bout = new ByteArrayOutputStream(10240);
        byte[] read_buf = new byte[1024];
        int read_len = 0;
        while ((read_len = s3is.read(read_buf)) > 0) {
            bout.write(read_buf, 0, read_len);
        }
        s3is.close();
        bout.close();

        return bout.toString();

    }
}
