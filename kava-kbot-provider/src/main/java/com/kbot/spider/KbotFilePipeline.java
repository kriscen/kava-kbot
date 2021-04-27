//package com.kbot.spider;
//
//import com.kbot.entity.spider.KbotFileType;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import us.codecraft.webmagic.ResultItems;
//import us.codecraft.webmagic.Task;
//import us.codecraft.webmagic.pipeline.Pipeline;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Map;
//
//@Slf4j
//@Data
//@AllArgsConstructor
//public class KbotFilePipeline implements Pipeline {
//    private String path;
//    private KbotFileType type;
//
//    @Override
//    public void process(ResultItems resultItems, Task task) {
//        if(KbotFileType.TXT.equals(type)){
//            doTxtExecute(resultItems,task);
//        }
//    }
//
//    /**
//     * 处理txt文件
//     * @param resultItems
//     * @param task
//     */
//    private void doTxtExecute(ResultItems resultItems, Task task) {
//        log.info("-------------------");
//        log.info(path);
//        Map<String, Object> itemsAll = resultItems.getAll();
//        String imageUrl = itemsAll.get("imageUrl").toString();
//        log.info(imageUrl);
//        downloadImage(imageUrl,path);
//    }
//
//
//    public void downloadImage(String url, String path) {
//        CloseableHttpClient client = HttpClientBuilder.create().build();
//        HttpGet get = new HttpGet(url);
//        FileOutputStream output = null;
//        try {
//            CloseableHttpResponse response = client.execute(get);
//            if (200 == response.getStatusLine().getStatusCode()) {
//                File storeFile = new File(path);
//                output = new FileOutputStream(storeFile);
//                // 得到网络资源的字节数组,并写入文件
//                HttpEntity responseEntity = response.getEntity();
//                output.write(EntityUtils.toByteArray(responseEntity));
//            } else {
//                System.out.println(response.getStatusLine().getStatusCode());
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }finally {
//            try {
//                output.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                client.close();
//            } catch (IOException e) {
//            }
//        }
//    }
//}
