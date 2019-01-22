
package example.sorter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SorterApplication implements ApplicationRunner {

	@Value("${content.server.url}")
	String contentUrl;

	RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(SorterApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String dirArg = args.getNonOptionArgs().get(0);
		Path path = Paths.get(dirArg);
		String name = path.getFileName().toString();

		Files.walk(path).parallel().filter(Files::isRegularFile).flatMap(f -> {
			try {
				return Files.lines(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Stream.empty();
		}).forEach((s) -> this.addContent(name,s));
	}

	private void addContent(String name, String s) {
		restTemplate.postForLocation(String.format("%s/content?key=%s&value=%s", contentUrl,name,s), null);
	}

}