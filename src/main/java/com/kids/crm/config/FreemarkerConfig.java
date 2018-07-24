package com.kids.crm.config;

import freemarker.template.TemplateModelException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreemarkerConfig {


    public FreemarkerConfig(freemarker.template.Configuration configuration, Config config) throws TemplateModelException {
        configuration.setSharedVariable("APP_NAME", config.getCompanyName());
    }
}
