package emma.galzio.movieratingsservice.restResource;

import emma.galzio.movieratingsservice.model.Rating;
import emma.galzio.movieratingsservice.model.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratings-data")
public class MovieRatingsResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    //No es bueno retornar directamente una lista en el cuerpo de una respuesta HTTP para una API
    //Es mejor wrappear esa lista en un objeto que la contenga, de esa forma, cuando sea necesario agregar campos para mejorar
    //La funcionalidad de la API no se va a romper todo en el cliente, sino que en lugar de eso simplmenente se va a agregar un campo
    //A la clase y al objeto que se devuelve.
    //Además, en caso de que el cliente sea en spring boot, el procedimiento para recuperar una lista es más complejo.
    @GetMapping("/users/{userID}")
    public UserRatings getRatings(@PathVariable("userID") String userId){
        return new UserRatings(userId, Arrays.asList(new Rating("M1", 5), new Rating("M2", 4)));
    }
}
