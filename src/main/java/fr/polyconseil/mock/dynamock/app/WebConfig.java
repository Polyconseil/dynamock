package fr.polyconseil.mock.dynamock.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class WebConfig extends WebMvcConfigurerAdapter {

//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/").setViewName("/static/index.html");
//  }

//	  @Controller
//	    static class Routes {
//
//		@RequestMapping({
//		    "/mock",
//		    "/dashboard"
//		})
//		public String index() {
//		    return "forward:/static/index.html";
//		}
//	    }
//	  
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			if (!this.resourceProperties.isAddMappings()) {
//				logger.debug("Default resource handling disabled");
//				return;
//			}
//
//			Integer cachePeriod = this.resourceProperties.getCachePeriod();
//			if (!registry.hasMappingForPattern("/webjars/**")) {
//				registry.addResourceHandler("/webjars/**")
//						.addResourceLocations("classpath:/META-INF/resources/webjars/")
//						.setCachePeriod(cachePeriod);
//			}
//			if (!registry.hasMappingForPattern("/**")) {
//				registry.addResourceHandler("/**")
//						.addResourceLocations(RESOURCE_LOCATIONS)
//						.setCachePeriod(cachePeriod);
//			}
//		}
}
