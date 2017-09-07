package org.likexin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 数据转换器
 * @author likexin5
 *
 */
@Configuration
public class MessageConverter {
	
	@Bean
	public HandlerAdapter handlerAdapter(WebBindingInitializer webBindingInitializer){
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes );
		messageConverters.add(fastJsonHttpMessageConverter);
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		//参数及返回值处理
		requestMappingHandlerAdapter.setMessageConverters(messageConverters);
		//参数校验
		requestMappingHandlerAdapter.setWebBindingInitializer(webBindingInitializer);
		return requestMappingHandlerAdapter;
	}
	
	@Bean
	public WebBindingInitializer initWebBindingInitializer(){
		ConfigurableWebBindingInitializer webBindingInitializer = new ConfigurableWebBindingInitializer();
		return webBindingInitializer;
	}
}
