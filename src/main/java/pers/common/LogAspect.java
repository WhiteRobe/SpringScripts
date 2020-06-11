package pers.common;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class LogAspect {

    public void log(){
        Logger l = LogManager.getLogger(LogAspect.class);
        l.debug("output");

    }
}
