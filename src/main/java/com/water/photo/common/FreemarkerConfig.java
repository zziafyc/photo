package com.water.photo.common;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 扩展FreeMarker使其拥有继承的特性
 *
 * @author https://github.com/zziaguan/
 */
@Configuration
public class FreemarkerConfig {

    private final freemarker.template.Configuration configuration;

    @Autowired
    public FreemarkerConfig(freemarker.template.Configuration configuration) {
        this.configuration = configuration;
    }

    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
    }
}
