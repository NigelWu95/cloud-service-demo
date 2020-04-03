package cn.net.nigel.aws;

import cn.net.nigel.common.Config;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.IOException;
import java.util.*;

public class S3ListerDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String AWSAccessKeyId = config.getValue("AWSAccessKeyId");
        String AWSSecretKey = config.getValue("AWSSecretKey");
        String bucket = config.getValue("bucket");
        String clientRegion = "ap-southeast-1";
        AWSCredentials credentials = new BasicAWSCredentials(AWSAccessKeyId, AWSSecretKey);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(clientRegion)
//                .withClientConfiguration()
                .build();
        System.out.println(s3Client.generatePresignedUrl(bucket, "", new Date()));
//        S3Lister s3Lister = new S3Lister(s3Client, bucket, "", "", "", "", 10);
//
//        System.out.println(s3Lister.currentEndKey());
//        while (s3Lister.hasNext()) {
//            s3Lister.updateMarkerBy(s3Lister.currentLast());
//            s3Lister.listForward();
//            System.out.println(s3Lister.currentEndKey());
//        }
    }
}
