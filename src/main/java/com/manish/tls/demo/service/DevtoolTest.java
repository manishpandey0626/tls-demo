package com.manish.tls.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DevtoolTest {


    public String testService(){
        ObjectMapper objectMapper=new ObjectMapper();

        return "New class also complied with devtools";
    }
}
