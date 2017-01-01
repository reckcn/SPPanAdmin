package net.sppan.base.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
    	try {
    		configuration.setDateFormat("yyyy/MM/dd");  
            configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
			configuration.setSharedVariable("testSharedVariable", "111111111111111111111");
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
    }
}
