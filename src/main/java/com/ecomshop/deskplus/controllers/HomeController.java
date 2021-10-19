package com.ecomshop.deskplus.controllers;

import com.ecomshop.deskplus.web.rest.Response;
import com.ecomshop.deskplus.web.rest.SignupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: Sheik Syed Ali
 * Date: 14 Oct 2021
 */
@RestController
@RequestMapping("/test/")
public class HomeController {

    @GetMapping
    public String testMe(){
        return "Hello Sheik";
    }


    @RequestMapping(
            value = "/testme",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public Map<String, Object> signup(MultiValueMap test){
        System.out.println("Hello sheik!!! Im in");

////        ObjectMapper mapper = null;
//        try{
//
////            mapper = new ObjectMapper();
////            String data = mapper.writeValueAsString(test);
////            BufferedWriter writer = new BufferedWriter(new FileWriter("testme.out"));
////            writer.write(data);
////            writer.close();
////            Iterator it = test.keySet().iterator();
////            while (it.hasNext())
////            {
////                String id = (String)it.next();
////                System.out.println("Sheiksyed: --"+id);
//////                Object values = test.get(id);
//////                System.out.println("Sheik --> ["+id+"] ["+values+"]");
////            }
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        } finally {
////            mapper = null;
//        }

        return null;
    }

    @GetMapping
    @RequestMapping("/testmeout")
    public String admin(){
        String out = "";
        try{
            InputStream is = new FileInputStream("testme.out");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            out = sb.toString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return out;
    }

}
