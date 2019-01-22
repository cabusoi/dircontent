package example.content;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ContentServerApplication {

	Map<String, ConcurrentSkipListSet<String>> content = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(ContentServerApplication.class, args);
	}

	@PostMapping(path = { "/content"})
	public void post(@RequestParam("key") @NotBlank String key, @RequestParam(name = "value") @NotBlank String value) {
		if (!content.containsKey(key)) {
			content.put(key, new ConcurrentSkipListSet<>());
		}
		content.get(key).add(value);
	}

	@GetMapping(path = { "/key"})
	public Set<String> get(@RequestParam("key") @NotBlank String key) {
		ConcurrentSkipListSet<String> result = content.get(key);
		return result;
		
	}

}
