package webbangiaydabong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import webbangiaydabong.service.SetUpdataService;

public class ServletInitializer extends SpringBootServletInitializer {
@Autowired
	SetUpdataService setUpdataService;
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		setUpdataService.Setupdata();
		return application.sources(DatnApplication.class);
	}

}
