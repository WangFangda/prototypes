package prototype.endpoint;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prototype.model.Restaurant;
import prototype.repo.RestaurantRepository;
/**
 * @author fangda.wang
 */
@RestController
public class ExampleController {
	@Autowired
	private RestaurantRepository repo;

	@GetMapping(path = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exec() {
		Restaurant[] restaurants = {
				new Restaurant(1L, "first", new Point(1, 1)),
				new Restaurant(2L, "second", new Point(-20, -20)),
				new Restaurant(3L, "third", new Point(100, 100))
		};

		repo.save(Arrays.asList(restaurants));

		Stream.of(restaurants).forEach(System.out::println);
	}

	@GetMapping(path = "/searchNearBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant> exec(
			@RequestParam("lat") double latitude,
			@RequestParam("lon") double longitude,
			@RequestParam("dis") double distance
	) {
		val point = new Point(latitude, longitude);
		val radius = new Distance(distance, Metrics.NEUTRAL);
		return repo.findByLocationNear(point, radius);

	}
}
