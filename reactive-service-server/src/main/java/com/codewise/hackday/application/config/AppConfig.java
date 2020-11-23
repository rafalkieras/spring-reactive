package com.codewise.hackday.application.config;

import com.codewise.hackday.business.RestPackageBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Configuration
@ComponentScan(basePackageClasses = RestPackageBase.class)
public class AppConfig {

}
