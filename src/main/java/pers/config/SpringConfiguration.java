package pers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"pers.controller", "pers.service", "pers.model", "pers.aspect", "pers.common"})
public class SpringConfiguration {

}
