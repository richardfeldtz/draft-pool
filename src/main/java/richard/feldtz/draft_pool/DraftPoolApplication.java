package richard.feldtz.draft_pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DraftPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DraftPoolApplication.class, args);
	}

}
