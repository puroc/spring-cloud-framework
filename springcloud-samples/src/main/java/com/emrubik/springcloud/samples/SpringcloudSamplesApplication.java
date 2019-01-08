package com.emrubik.springcloud.samples;

import com.emrubik.springcloud.api.annotation.EnableFeignApi;
import com.emrubik.springcloud.common.annotation.EnableCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableCommon
@EnableFeignApi
@SpringCloudApplication
public class SpringcloudSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudSamplesApplication.class, args);
	}
}
