package com.kot;

import com.kbot.KbotApplication;
import com.kbot.service.ShareApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class ShareApiTest {
    @Autowired
    private ShareApiService bcrCalendarApiService;

    @Test
    public void bcrTest(){
        System.out.println(bcrCalendarApiService.extract());
    }
}