package com.kids.crm.config;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreemarkerConfig {


    public FreemarkerConfig(freemarker.template.Configuration configuration, Config config) throws TemplateModelException {
        configuration.setSharedVariable("APP_NAME", config.getCompanyName());

        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel questionKey =
                (TemplateHashModel) staticModels.get("com.kids.crm.pojo.QuestionKey");
        configuration.setSharedVariable("QUESTION_KEY", questionKey);

    }
}
