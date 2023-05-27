package emma.galzio.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}


	/*La annotation @LoadBalanced lo que hace es decirle al RestTemplate que en realidad tiene que
	* la URL que le pasamos no es realmente una URL, sino un indicio de la URL que debe buscar en el
	* servidor de descrubrimiento. Spring Cloud usa descubrimiento del lado del cliente, pero oculta la resolución
	* de la url así que es como si fuera del lado del servidor*/
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplare(){
		return new RestTemplate();
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

}
