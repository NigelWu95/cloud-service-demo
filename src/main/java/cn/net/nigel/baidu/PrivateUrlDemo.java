package cn.net.nigel.baidu;

import cn.net.nigel.common.Config;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.GeneratePresignedUrlRequest;

import java.io.IOException;

public class PrivateUrlDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String accessId = config.getValue("bai-id");
        String secretKey = config.getValue("bai-secret");
        String bucket = config.getValue("bucket");
//        accessId = "ads";
        bucket = "nigel-test";
        BosClientConfiguration clientConfiguration = new BosClientConfiguration();
        clientConfiguration.setCredentials(new DefaultBceCredentials(accessId, secretKey));
        BosClient bosClient = new BosClient(clientConfiguration);
        try {
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, "");
            System.out.println(bosClient.generatePresignedUrl(request));
        } finally {
            bosClient.shutdown();
        }
    }
}
