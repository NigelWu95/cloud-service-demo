package cn.net.nigel.upyun;

import cn.net.nigel.common.Config;
import com.UpYun;

import java.util.Base64;
import java.util.List;

public class ListFilesDemo {

    public static void main(String[] args) throws Exception {

        Config config = new Config(".config.properties");
        String name = config.getValue("up-id");
        String secret = config.getValue("up-secret");
        String bucket = config.getValue("bucket");
        String prefix = null;
        prefix = "yflb";
        UpConfig upConfig = new UpConfig();
        UpYunClient upYunClient = new UpYunClient(upConfig, name, secret);
        UpLister upLister = new UpLister(upYunClient, bucket, prefix, null, null, 1000);
        while (upLister.hasNext()) {
            for (FolderItem folderItem : upLister.currents()) {
                System.out.println(folderItem.key + "\t" + folderItem.attribute);
            }
            upLister.listForward();
        }
        System.out.println("--------");
        for (FolderItem folderItem : upLister.currents()) {
            System.out.println(folderItem.key + "\t" + folderItem.size);
        }

//        Base64.Decoder decoder = Base64.getDecoder();
//        String keyString = new String(decoder.decode("g2gCZAAEbmV4dGQAA2VvZg"));
//        System.out.println(keyString);
//
//        System.out.println(upLister.currentEndKey());
//        upLister.updateMarkerBy(upLister.currentLast());
//
//        UpYun upYun = new UpYun(bucket, name, secret);
//        List<UpYun.FolderItem> list = upYun.readDir("wkt_cloud2", null);
//        System.out.println(upYun.getFileInfo("listbucket.go"));
    }
}
