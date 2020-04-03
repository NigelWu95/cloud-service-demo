package cn.net.nigel.aliyun;

import cn.net.nigel.common.Config;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.io.IOException;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) throws IOException {

        Config config = new Config(".config.properties");
        String accessKeyId = config.getValue("ali-id");
        String accessKeySecret = config.getValue("ali-secret");
        String bucket = config.getValue("bucket");
//        bucket = "nigel";
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String keyPrefix = "188";
//        keyPrefix = null;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucket).withMaxKeys(1000)
                .withPrefix(keyPrefix)
                .withEncodingType("url"); // url 解码特殊字符报 400 的问题
                  //           18126095125_1ba394197e91ec2dcd4345a6a901b232_CQQ-PC_1520849977_3.0.9.zip
//                .withMarker("18126095125_1ba394197e91ec2dcd4345a6a901b232_CQQ-PC_1520849977_3.0.9.zip");
        ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
        System.out.println(objectListing.getNextMarker());
//        while (objectListing.isTruncated()) {
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
            objectListing = ossClient.listObjects(listObjectsRequest);
//        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
