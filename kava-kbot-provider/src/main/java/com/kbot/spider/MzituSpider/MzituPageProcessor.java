package com.kbot.spider.MzituSpider;

import com.kbot.entity.spider.KbotFileType;
import com.kbot.spider.KbotFilePipeline;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.regex.Pattern;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: mzitu - 由于防盗链 以及服务器限制  展示不做
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Slf4j
public class MzituPageProcessor implements PageProcessor {
    private final Pattern listPattern = Pattern.compile("(https://www.mzitu.com/)[A-Za-z]+(/)");
    private final Pattern detailPattern = Pattern.compile("(https://www.mzitu.com/)[\\d+]");

    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        String url = page.getHtml().xpath("//div[@class='main-image']/p/a/img/@src").toString();
        if(detailPattern.matcher(page.getUrl().toString()).find()){
            page.putField("imageUrl", url.trim());
        }else {
            page.setSkip(true);
        }
        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().css("div.postlist").links().regex("(https://www\\.mzitu\\.com/[\\w]+(/)?[\\w]*(/)?)").all());
        page.addTargetRequests(page.getHtml().css("div.pagination").links().regex("(https://www\\.mzitu\\.com/[\\w]+(/)?[\\w]*(/)?)").all());
        page.addTargetRequests(page.getHtml().css("div.pagenavi").links().regex("(https://www\\.mzitu\\.com/[\\w]+(/)?[\\w]*(/)?)").all());
    }

    @Override
    public Site getSite() {
        // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
        return Site.me()
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36")
                .addHeader("accept-encoding","gzip, deflate, br")
                .addHeader("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .addHeader("referer","https://www.mzitu.com/236049/3")
                .addHeader("sec-fetch-dest","empty")
                .addHeader("sec-fetch-mode","cors")
                .addHeader("sec-fetch-site","same-origin")
                .setSleepTime(1000);
    }

    public static void main(String[] args) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",4780)));
        Spider.create(new MzituPageProcessor())
                .addPipeline(new KbotFilePipeline("C:\\Users\\wznnk\\Desktop\\11", KbotFileType.TXT))
                .addUrl("https://www.mzitu.com")
                .setDownloader(httpClientDownloader)
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();
//        Spider.create(new MzituPageProcessor()).addPipeline(new ConsolePipeline())
//                .test("https://www.mzitu.com/");
    }
}
