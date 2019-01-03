package com.emrubik.springcloud.api.configuration;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

//OkHttp的拦截器
@Component
public class OkHttpTokenInterceptor implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
	    Response response = chain.proceed(request);
		return response;
	}

}