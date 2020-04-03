package cn.net.nigel.aws;

import cn.net.nigel.common.Config;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.internal.DefaultServiceEndpointBuilder;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.qcloud.cos.auth.AbstractCOSCachedCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Date;

public class RegionDemo {

    private static final Logger logger = LoggerFactory.getLogger(RegionDemo.class);

    public static void main(String[] args) throws IOException {

        System.out.println(Regions.fromName("us-east-1"));
        System.out.println(RegionUtils.getRegion("cn-north-1"));

        Config config = new Config(".config.properties");
//        String AWSAccessKeyId = config.getValue("AWSAccessKeyId");
//        String AWSSecretKey = config.getValue("AWSSecretKey");
//        String bucket = config.getValue("bucket");
        String AWSAccessKeyId = config.getValue("ak");
        String AWSSecretKey = config.getValue("sk");
        String bucketName = config.getValue("bucket");
        bucketName = "temp";
//        String clientRegion = "us-east-1";
        String endpoint = "s3-cn-east-1.qiniucs.com";
//        String endpoint = "s3-cn-north-1.qiniucs.com";
        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(endpoint, "cn-north-1");
        AWSCredentials credentials = new BasicAWSCredentials(AWSAccessKeyId, AWSSecretKey);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);

//        final String serviceNameForEndpoint = AwsClientBuilder.getEndpointPrefix();
//        final String serviceNameForSigner = getServiceNameIntern();
//        URI uri = new DefaultServiceEndpointBuilder(serviceNameForEndpoint, clientConfiguration.getProtocol()
//                .toString()).withRegion(region).getServiceEndpoint();

        AmazonS3ClientBuilder amazonS3ClientBuilder = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConfiguration);
        AmazonS3 s3Client = amazonS3ClientBuilder
//                .withRegion("us-east-1")
                .build();
        AmazonS3 s3Client1 = amazonS3ClientBuilder.build();
        AmazonS3 s3Client2 = amazonS3ClientBuilder.build();
        AmazonS3 s3Client3 = amazonS3ClientBuilder.build();

        System.out.println(s3Client.generatePresignedUrl(bucketName, "test.gif", new Date()));
        System.out.println(s3Client.getBucketLocation(bucketName));
//        for (S3ObjectSummary s3ObjectSummary : s3Client.listObjects(bucketName).getObjectSummaries()) {
//            System.out.println("object: " + s3ObjectSummary.getKey());
//        }

        AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(AWSAccessKeyId, AWSSecretKey));
        s3.setEndpoint(endpoint);
//        s3.setEndpoint("s3-cn-north-1.qiniucs.com");
        S3ClientOptions options = new S3ClientOptions();
        options.withChunkedEncodingDisabled(true);
        s3.setS3ClientOptions(options);
        for (S3ObjectSummary s3ObjectSummary : s3.listObjects(bucketName).getObjectSummaries()) {
            System.out.println("object: " + s3ObjectSummary.getKey());
        }
        for (Bucket bucket : s3.listBuckets()) {
            System.out.println(" - " + bucket.getName());
        }
        System.out.println(s3.getBucketLocation(bucketName));

        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
