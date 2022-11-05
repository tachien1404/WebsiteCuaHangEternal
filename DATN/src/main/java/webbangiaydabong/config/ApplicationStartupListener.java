package webbangiaydabong.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import webbangiaydabong.service.SetUpdataService;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {
    private static boolean eventFired = false;
    @Autowired
    SetUpdataService setUpdataService;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

    @Override
    public void afterPropertiesSet() throws Exception {


    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (eventFired) {
            return;
        }

        logger.info("Tạ Quang Tuấn");

        eventFired = true;

        setUpdataService.Setupdata();
    }
}
