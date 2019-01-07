package com.example.demo.hc.json;

import com.example.demo.hc.entity.ResultJson;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 通过用户名获取余额
 */
public class EosJson {

    public  Double getJson(String username) {
        BufferedReader reader = null;
        try {
            URL url = new URL("https://api.eospark.com/api?module=account&action=get_account_balance&apikey=a9564ebc3289b7a14551baf8ad5ec60a&account="+username);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            Gson gson = new Gson();
            Thread.sleep(80);
            ResultJson page = gson.fromJson(buffer.toString(), ResultJson.class);
            if(page.getData()==null){
                Thread.sleep(100);
                Double d = getJson(username);
                System.out.println(d+" =="+username);
                return d;
            }
            System.out.println(page.getData().getBalance()+" --"+username);
            return page.getData().getBalance();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
