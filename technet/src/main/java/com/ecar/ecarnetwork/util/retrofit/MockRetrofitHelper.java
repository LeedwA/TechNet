package com.ecar.ecarnetwork.util.retrofit;


import com.ecar.ecarnetwork.util.file.FileUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MockRetrofitHelper {

    public <T> T create(Class<T> clazz) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(path)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    private String path="";

    public void setPath(String path) {
        this.path = path;
    }

    private class MockInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            // 模拟网络数据
            String content = null;
            try {
                content = FileUtil.readTextFile(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
           // String content = AssestsReader.readFile();
//            String content = "[{\"name\": \"hello\"}]";

            ResponseBody body = ResponseBody.create(MediaType.parse("application/x-www-form-urlencoded"), content);

            Response response = new Response.Builder().request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .body(body)
                    .build();
            return response;
        }
    }
}