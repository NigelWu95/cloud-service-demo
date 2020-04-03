package cn.net.nigel.huawei;

import cn.net.nigel.common.Config;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ObjectListing;
import com.obs.services.model.ObsObject;

public class ListFilesDemo {

    public static void main(String[] args) throws Exception {

        Config config = new Config(".config.properties");
        String accessId = config.getValue("hua-id");
        String secretKey = config.getValue("hua-secret");
        String bucket = config.getValue("bucket");
        bucket = "css-backup-1544044401924";
        String endPoint = "https://obs.cn-north-1.myhuaweicloud.com";
        ObsConfiguration configuration = new ObsConfiguration();
        configuration.setEndPoint(endPoint);
        final ObsClient obsClient = new ObsClient(accessId, secretKey, configuration);

//        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucket, 50);
//        listObjectsRequest.setPrefix("css_repository/Es-1568/snap-8_");
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucket, 1);
        ObjectListing result = obsClient.listObjects(listObjectsRequest);
        System.out.println("nextMarker: " + result.getNextMarker());
        for(ObsObject obsObject : result.getObjects()){
            System.out.println(obsObject.getObjectKey() + "\t" + obsObject.getOwner().toString());
            System.out.println("\t" + obsObject.getOwner());
        }
    }
}
