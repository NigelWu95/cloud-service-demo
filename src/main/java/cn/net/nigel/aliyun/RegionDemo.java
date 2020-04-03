package cn.net.nigel.aliyun;

import cn.net.nigel.common.Config;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;

import java.io.IOException;
import java.util.Date;

public class RegionDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String accessKeyId = config.getValue("ali-id");
        String accessKeySecret = config.getValue("ali-secret");
        String endpoint = config.getValue("endpoint");
        String bucket = config.getValue("bucket");
        bucket = "nigel";
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        OSSClient ossClient = new OSSClient("oss-cn-shanghai.aliyuncs.com", credentialsProvider, clientConfiguration);
        System.out.println(ossClient.getBucketLocation(bucket));
        ossClient = new OSSClient("oss-cn-shanghai.aliyuncs.com", credentialsProvider, null);
        System.out.println(ossClient.generatePresignedUrl(bucket, "test.txt", new Date(new Date().getTime() + 3600 * 1000)));
    }
}
