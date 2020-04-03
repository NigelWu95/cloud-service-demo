package cn.net.nigel.aws;

import cn.net.nigel.common.Config;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.File;
import java.io.IOException;

public class UploadDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String AWSAccessKeyId = config.getValue("ak");
        String AWSSecretKey = config.getValue("sk");
        String bucketName = config.getValue("bucket");
        String endpoint = "s3-cn-east-1.qiniucs.com";
        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(endpoint, "cn-east-1");
        AWSCredentials credentials = new BasicAWSCredentials(AWSAccessKeyId, AWSSecretKey);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConfiguration)
                .build();
        PutObjectResult putObjectResult = s3Client.putObject(bucketName, "tests3.txt",
                new File("/Users/wubingheng/Downloads/react.txt")
        );
        System.out.println(putObjectResult.getETag());
    }
}
