### httpclient 
    跨平台调用第三方接口<sub>wjl</sub>
####  pom依赖
```html

        <dependency>
            <groupId>commons-httpclient</groupId>
            <artifactId>commons-httpclient</artifactId>
            <version>3.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpmime</artifactId>
            <version>4.5.3</version>
        </dependency>


```

####  示例代码
```java

public void  getConnect(){

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/file/addFile");

        String fileInfo="/usr/root/personl/20190811";
        File file=new File("D:\\upload\\**.xls");
        System.out.println(file.getName());
        String s = null;
        try {
            MultipartEntityBuilder  builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(CharsetUtils.get("UTF-8"));
            builder.addTextBody("path",fileInfo,ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            builder.addBinaryBody("file", new FileInputStream(file), ContentType.MULTIPART_FORM_DATA, file.getName());// 文件流
            HttpEntity httpEntity = builder.build();
            httpPost.setEntity(httpEntity);
            HttpResponse response=httpclient.execute(httpPost);
            System.out.println(JSONObject.toJSONString( response.getStatusLine().getStatusCode()));
            System.out.println( response.getStatusLine());
            HttpEntity entity = response.getEntity();
            s = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);

    }

```

##### 后端接口双参数：
  > (@RequestParam("path") String path, @RequestParam("file")  MultipartFile file)

 > 同理 如果多个不同参数:
 
  > (@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime)
```java
     MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
     builder.addTextBody("startTime", startTime, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
     builder.addTextBody("endTime", endTime, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
     HttpEntity httpEntity=builder.build();
     httpPost.setEntity(httpEntity);
     HttpResponse response=httpclient.execute(httpPost);
```
  
 #### 后端dto接收（@RequestBody Dto dto）
 
 ```java
     httpPost.addHeader("Content-Type", "application/json");
     httpPost.setEntity(new StringEntity(JSON.toJSONString(dto),"UTF-8")); //防止中文乱码
     CloseableHttpResponse response = httpclient.execute(httpPost);
 
```
 