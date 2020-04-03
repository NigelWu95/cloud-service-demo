package cn.net.nigel.upyun;

import cn.net.nigel.common.Config;
import com.UpYun;
import com.upyun.UpException;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class UpYunDemo {

    public static void main(String[] args) throws IOException, UpException {

        Config config = new Config(".config.properties");
        String name = config.getValue("upyun-name");
        String secret = config.getValue("upyun-secret");
        String bucket = config.getValue("bucket");

        UpYun upYun = new UpYun(bucket, name, secret);
        List<UpYun.FolderItem> list = upYun.readDir("squirrel/material/20170728/FF8080815D86D538015D870CE3CB0011.奇艺网-神秘代码-0001%20(1)", null);
    }
}
