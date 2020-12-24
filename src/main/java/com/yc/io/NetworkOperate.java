package com.yc.io;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * @Author yucheng
 * @Date 2020/12/20 14:22
 */
public class NetworkOperate {

    public static void main(String[] args) throws Exception {
        System.out.println(JSON.toJSONString(InetAddress.getByName("localhost")));
//        System.out.println(JSON.toJSONString(InetAddress.getByAddress("localhost".getBytes("UTF-8"))));

        URL url = new URL("https://cn.bing.com/");
        //inputStream -> inputStreamReader -> bufferedReader
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String something;

        while ((something = bufferedReader.readLine()) != null) {
            System.out.println(something);
        }
        bufferedReader.close();

    }
}
