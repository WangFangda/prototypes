package prototype.repo;
import java.util.List;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import prototype.model.Restaurant;
/**
 * @author fangda.wang
 */
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

	Restaurant findByName(String name);
	List<Restaurant> findByLocationNear(Point center, Distance radius);

}
