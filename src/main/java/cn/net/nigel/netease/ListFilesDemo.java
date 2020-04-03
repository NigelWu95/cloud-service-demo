package cn.net.nigel.netease;

import com.netease.cloud.ClientConfiguration;
import com.netease.cloud.Protocol;
import com.netease.cloud.auth.BasicCredentials;
import com.netease.cloud.auth.Credentials;
import com.netease.cloud.services.nos.NosClient;
import com.netease.cloud.services.nos.model.NOSObjectSummary;
import com.netease.cloud.services.nos.model.ObjectListing;

import java.util.List;

public class ListFilesDemo {

    public static void main(String[] args) {
        String accessKey = "your-accesskey";
        String secretKey = "your-secretKey ";
        String endPoint = "endPoint";
        String bucket = "bucket";
        Credentials credentials = new BasicCredentials(accessKey, secretKey);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConnections(200);
        conf.setSocketTimeout(10000);
        conf.setMaxErrorRetry(2);
        conf.setProtocol(Protocol.HTTPS);
        NosClient nosClient = new NosClient(credentials, conf);
        nosClient.setEndpoint(endPoint);
        ObjectListing objectListing = nosClient.listObjects(bucket);
        List<NOSObjectSummary> sums = objectListing.getObjectSummaries();
        for (NOSObjectSummary s : sums) {
            System.out.println("\t" + s.getKey());
        }

        nosClient.getBucketLocation(bucket);
    }
}
