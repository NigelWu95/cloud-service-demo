package cn.net.nigel.huawei;

import cn.net.nigel.common.Config;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;

public class HRegionDemo {

    public static void main(String[] args) throws Exception {

        Config config = new Config(".config.properties");
        String accessId = config.getValue("hua-id");
        String secretKey = config.getValue("hua-secret");
        String bucket = config.getValue("bucket");
        bucket = "css-backup-1544044401924";
        String endPoint = "https://obs.myhuaweicloud.com";

        ObsConfiguration configuration = new ObsConfiguration();
        configuration.setEndPoint(endPoint);
        ObsClient obsClient = new ObsClient(accessId, secretKey, configuration);
        System.out.println(obsClient.getBucketLocation(bucket));
    }
}
