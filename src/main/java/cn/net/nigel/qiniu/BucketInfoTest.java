package cn.net.nigel.qiniu;

import cn.net.nigel.common.Config;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.BucketInfo;
import com.qiniu.util.Auth;

public class BucketInfoTest {

    public static void main(String[] args) throws Exception {

        Config config = new Config(".config.properties");
        String ak = config.getValue("ak");
        String sk = config.getValue("sk");
        BucketManager bucketManager = new BucketManager(Auth.create(ak, sk), new Configuration());
        BucketInfo bucketInfo = bucketManager.getBucketInfo("temp");
        System.out.println(bucketInfo.getRegion());
    }
}
