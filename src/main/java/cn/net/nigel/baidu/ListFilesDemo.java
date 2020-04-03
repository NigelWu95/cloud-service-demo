package cn.net.nigel.baidu;

import cn.net.nigel.common.Config;
import com.baidubce.BceServiceException;
import com.baidubce.Region;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.ListObjectsRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;

import java.io.IOException;

public class ListFilesDemo {

    public static void main(String[] args) throws IOException {
        test();
    }

    public static String test() throws IOException {

        Config config = new Config(".config.properties");
        String accessId = config.getValue("bai-id");
        String secretKey = config.getValue("bai-secret");
        String bucket = config.getValue("bucket");
//        accessId = "ads";
        bucket = "nigel-test";
        BosClientConfiguration clientConfiguration = new BosClientConfiguration();
        clientConfiguration.setCredentials(new DefaultBceCredentials(accessId, secretKey));
        BosClient bosClient = new BosClient(clientConfiguration);
        // 指定最大返回条数为500
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucket);
        listObjectsRequest.withMaxKeys(50);
        try {
            String region = bosClient.getBucketLocation(bucket).getLocationConstraint();
//            System.out.println(region);
            clientConfiguration.setEndpoint(region + ".bcebos.com");
//            clientConfiguration.setRegion(Region.fromValue("bj"));
            bosClient.shutdown();
//            clientConfiguration.setEndpoint("su.bcebos.com");
            bosClient = new BosClient(clientConfiguration);
            ListObjectsResponse listObjectsResponse = bosClient.listObjects(listObjectsRequest);
            System.out.println("marker: " + listObjectsResponse.getNextMarker());
            for (BosObjectSummary objectSummary : listObjectsResponse.getContents()) {
                System.out.println("ObjectKey:" + objectSummary.getKey());
                System.out.println(" —— " + objectSummary.getOwner());
            }
            return null;
        } catch (BceServiceException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getStatusCode());
            throw e;
        } finally {
            bosClient.shutdown();
        }
    }
}
