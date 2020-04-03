package cn.net.nigel.cos;

import cn.net.nigel.common.Config;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.region.Region;

import java.io.IOException;

public class ListObjectsDemo {

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
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucket, null, null, null, 100);
        ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);
        System.out.println("marker: " + objectListing.getNextMarker());
        for (COSObjectSummary cosObjectSummary : objectListing.getObjectSummaries()) {
            System.out.println(cosObjectSummary.getKey() + "\t" + cosObjectSummary.getLastModified());
            System.out.println(" - " + cosObjectSummary.getOwner());
        }
    }
}
