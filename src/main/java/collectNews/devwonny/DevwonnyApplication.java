package collectNews.devwonny;

import collectNews.devwonny.config.MyBatisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Import(MyBatisConfig.class)
//@SpringBootApplication(scanBasePackages = "collectNews.devwonny.web")
@EnableScheduling
@SpringBootApplication
public class DevwonnyApplication {

	public static void main(String[] args) {

		SpringApplication.run(DevwonnyApplication.class, args);
	}

}
