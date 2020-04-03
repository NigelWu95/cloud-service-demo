package cn.net.nigel.huawei;

import cn.net.nigel.common.Config;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.model.TemporarySignatureRequest;

import java.io.IOException;

public class PrivateUrlDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String accessId = config.getValue("hua-id");
        String secretKey = config.getValue("hua-secret");
        String bucket = config.getValue("bucket");
        bucket = "css-backup-1544044401924";
        String endPoint = "https://obs.cn-north-1.myhuaweicloud.com";
        ObsConfiguration configuration = new ObsConfiguration();
        configuration.setEndPoint(endPoint);
        final ObsClient obsClient = new ObsClient(accessId, secretKey, configuration);
        TemporarySignatureRequest request = new TemporarySignatureRequest();
        request.setBucketName(bucket);
        request.setObjectKey("");
        System.out.println(obsClient.createTemporarySignature(request).getSignedUrl());
    }
}
