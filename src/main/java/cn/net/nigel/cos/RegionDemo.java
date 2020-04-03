package cn.net.nigel.cos;

import cn.net.nigel.common.Config;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.region.Region;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class RegionDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String secretId = config.getValue("ten-id");
        String secretKey = config.getValue("ten-secret");
        String bucket = config.getValue("bucket");
        bucket = "test-1254031816";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
//        ClientConfig clientConfig = new ClientConfig(new Region());
        COSClient cosClient = new COSClient(cred, clientConfig);
        String region = null;
        region = cosClient.getBucketLocation(bucket);
        // 腾讯 cos sdk listBuckets 不进行分页列举，账号空间个数上限为 200，可一次性列举完
        List<Bucket> list = cosClient.listBuckets();
        for (Bucket eachBucket : list) {
            if (eachBucket.getName().equals(bucket)) {
                region = eachBucket.getLocation();
                break;
            }
        }
        System.out.println(region);
        cosClient.shutdown();

        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, "target/java-programming-1.0-SNAPSHOT.jar");
        request.addRequestParameter("a", "b");
        request.addRequestParameter("v", "");
        GeneratePresignedUrlRequest request1 = (GeneratePresignedUrlRequest) request.clone();
        GeneratePresignedUrlRequest request2 = (GeneratePresignedUrlRequest) request.clone();
        GeneratePresignedUrlRequest request3 = (GeneratePresignedUrlRequest) request.clone();
        request.setBucketName("abc");
        System.out.println(request.getBucketName());
        System.out.println(request1.getBucketName());
        System.out.println(request2.getBucketName());
        System.out.println(request3.getBucketName());
        request.setKey("abc");
        System.out.println(request.getKey());
        System.out.println(request1.getKey());
        System.out.println(request2.getKey());
        System.out.println(request3.getKey());
        System.out.println(cosClient.generatePresignedUrl(bucket, "", new Date()));;
    }
}
