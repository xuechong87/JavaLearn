package com.luckystars.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;


@SpringBootTest
@AutoConfigureMockMvc
public class MvcTest {
//    public static void main(String[] args) {
//
//        String[] d1={"1.0","1","-1.00","-1","0.0","0.1","9999999999999999999999999","abc","1.999"};
//
//        Pattern reg = Pattern.compile("^((-?[1-9]\\d{0,14})||-?[0-9])(\\.\\d{1,2})$");
//
//        for (int i = 0; i < d1.length; i++) {
//            System.out.println(d1[i] + reg.matcher(d1[i]).matches());
//        }
//    }

        @Autowired
        private MockMvc mvc;
        @Autowired
        MyProp myProp;

        @org.junit.jupiter.api.Test
        public void getHello() throws Exception {
            mvc.perform(MockMvcRequestBuilders.get("/")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(equalTo(myProp.getTestprop())));
        }
}
