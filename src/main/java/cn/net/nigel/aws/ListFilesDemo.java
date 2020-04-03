package cn.net.nigel.aws;

import cn.net.nigel.common.Config;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.IOException;

public class ListFilesDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String AWSAccessKeyId = config.getValue("AWSAccessKeyId");
        String AWSSecretKey = config.getValue("AWSSecretKey");
        String bucket = config.getValue("bucket");
        String clientRegion = "ap-southeast-1";
        try {
            AWSCredentials credentials = new BasicAWSCredentials(AWSAccessKeyId, AWSSecretKey);
            AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(credentialsProvider)
                    .withRegion(clientRegion)
                    .build();

            System.out.println("Listing objects");

            // maxKeys is set to 2 to demonstrate the use of
            // ListObjectsV2Result.getNextContinuationToken()
            ListObjectsV2Request req = new ListObjectsV2Request()
                    .withBucketName(bucket)
//                    .withContinuationToken("1Vtu5vEsvdsHBvyA/Y/z6ihTlTEqq9q5RuUi0J8N/c+oYQJiP/aRLt/8E8gXxFwTFy4O3O96Cou7/u/XQNHethzMjVhmRkW+8")
                    .withStartAfter("target/classes/cn/net/nigel/mysql/MysqlDemo.class")
//                    .withPrefix("")
                    .withMaxKeys(1);
            ListObjectsV2Result result;

            do {
                result = s3Client.listObjectsV2(req);

                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
                }
                // If there are more than maxKeys keys in the bucket, get a continuation token
                // and list the next objects.
                String token = result.getNextContinuationToken();
                System.out.println("Next Continuation Token: " + token);
                req.setContinuationToken(token);
            } while (result.isTruncated());
        } catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}
