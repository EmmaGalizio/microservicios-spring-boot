package emma.galzio.moviecatalogservice.restView;


import emma.galzio.moviecatalogservice.restView.model.CatalogItem;
import emma.galzio.moviecatalogservice.restView.model.Movie;
import emma.galzio.moviecatalogservice.restView.model.Rating;
import emma.galzio.moviecatalogservice.restView.model.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID){

        //En lugar de pasar la URL se le pasa el nombre del servicio como está registrado en el servidor
        UserRatings userRatings = restTemplate.getForObject("http://movie-ratings-service/ratings-data/users/".concat(userID), UserRatings.class);

        //Respuesta resultante de la llamada al servicio de ratings (movie-ratings-service)
        //List<Rating> ratingList = Arrays.asList(new Rating("M1", 5), new Rating("M2",3));
        List<Rating> ratingList = userRatings.getRatings();

        //Con RestTemplate se hace una llamada a la api que retorna un JSON con los datos de la película del ID que corresponda
        //RestTemplate puede hacer automáticamente el unmarshalling del JSON si la clase tiene la misma estructura
        //Necesita tener el constructor SIN ARGUMENTOS
        List<CatalogItem> catalog = ratingList.stream().map((rating -> {
            //Movie movie = restTemplate.getForObject("http://localhost:8090/movies/".concat(rating.getMovieId()), Movie.class);
        Movie movie = webClientBuilder.build().get().uri("htto://movie-info-service/movies/".concat(rating.getMovieId()))
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatalogItem(rating.getRating(), movie.getName(), "Descripcion");
        })).toList();

        return catalog;
    }
}
