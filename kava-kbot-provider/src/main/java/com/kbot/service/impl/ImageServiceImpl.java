package com.kbot.service.impl;

import com.google.common.collect.Maps;
import com.kbot.service.ImageService;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.Map;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 图片服务
 * <p>
 * Created by kris on 2021/4/17
 *
 * @author kris
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Image sendImage4Online(Contact sender,String url) {
        return sendImage4Online(sender,url, Maps.newHashMap());
    }

    @Override
    public Image sendImage4Online(Contact sender, String url, Map<String,String> header) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        byte[] bytes;
        Image image = null;
        InputStream is = null;
        ExternalResource resource = null;
        try {
            buildHeader(get,header);
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                HttpEntity responseEntity = response.getEntity();
                bytes = EntityUtils.toByteArray(responseEntity);
                is = new ByteArrayInputStream(bytes);
                resource = ExternalResource.create(is);
                image = sender.uploadImage(resource);
            } else {
                System.out.println("no pic");
            }
        } catch (Exception e) {
            System.out.println("no pic");
        }finally {
            try {
                resource.close();
            } catch (IOException e) {
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
            }
        }
        return image;
    }

    @Override
    public Image sendImage4Local(Contact sender,String path) {
        ExternalResource resource = null;
        Image image = null;
        File file = new  File(path);
        try {
            resource = ExternalResource.create(file);
            image = sender.uploadImage(resource);
        }catch (Exception e){
        }finally {
            resource.getClosed();
        }
        return image;
    }

    @Override
    public Image sendImage4Local(Contact sender, File file) {
        ExternalResource resource = null;
        Image image = null;
        try {
            resource = ExternalResource.create(file);
            image = sender.uploadImage(resource);
        }catch (Exception e){
        }finally {
            resource.getClosed();
        }
        return image;
    }

    @Override
    public Image sendImage4Local(Contact sender, InputStream is) {
        ExternalResource resource = null;
        Image image = null;
        try {
            resource = ExternalResource.create(is);
            image = sender.uploadImage(resource);
        }catch (Exception e){
        }finally {
            resource.getClosed();
        }
        return image;
    }

    @Override
    public Image sendImage4Local(Contact sender, byte[] bytes) {
        Image image = null;
        InputStream is = null;
        ExternalResource resource = null;
        try {
            is = new ByteArrayInputStream(bytes);
            resource = ExternalResource.create(is);
            image = sender.uploadImage(resource);
        } catch (Exception e) {
        }finally {
            try {
                resource.close();
            } catch (IOException e) {
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    @Override
    public void downloadImage(String url, String path) {
        downloadImage(url,path,Maps.newHashMap());
    }

    @Override
    public void downloadImage(byte[] bytes, String path) {
        FileOutputStream output = null;
        try {
            File storeFile = new File(path);
            output = new FileOutputStream(storeFile);
            output.write(bytes);
        } catch (Exception e) {
            System.out.println("no pic");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void downloadImage(String url, String path, Map<String, String> header) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        FileOutputStream output = null;
        try {
            buildHeader(get,header);
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                File storeFile = new File(path);
                output = new FileOutputStream(storeFile);
                // 得到网络资源的字节数组,并写入文件
                HttpEntity responseEntity = response.getEntity();
                output.write(EntityUtils.toByteArray(responseEntity));
            } else {
                System.out.println("no pic");
            }
        } catch (Exception e) {
            System.out.println("no pic");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
            }
        }
    }

    private void buildHeader(HttpRequestBase request,Map<String, String> params){
        if(CollectionUtils.isEmpty(params)){return;}
        params.forEach(request::addHeader);
    }
}
