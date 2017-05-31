package prototypes.redis.endpoint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import prototypes.redis.constants.GlobalConstants;
import prototypes.redis.enums.Cities;
import prototypes.redis.enums.Restaurants;
import prototypes.redis.enums.Types;
import prototypes.redis.exception.BadRequestException;
import prototypes.redis.exception.NotFoundException;
import prototypes.redis.model.Response;

/**
 * @author fangda.wang
 */
@RestController
public class ExampleController {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * Sorted Set
	 * @return
	 */
	@GetMapping(value = "/init")
	public List<Response> exec() {
		for (Restaurants restaurant: Restaurants.values()) {
			redisTemplate.opsForZSet().add(restaurant.type, restaurant.code, restaurant.score);
		}
		return getRanking();
	}

	@GetMapping(value = "/update/{restaurantCode}/{score}/")
	public Response update(HttpServletResponse httpResponse,
						   @PathVariable("restaurantCode") String restaurantCode,
						   @PathVariable("score") double score) {

		if (score > GlobalConstants.MAX_SCORE) {
			throw new BadRequestException();
		}

		val key = Restaurants.getType(restaurantCode);
		if (StringUtils.isEmpty(key)) {
			throw new NotFoundException();
		}

		redisTemplate.opsForZSet().add(key, restaurantCode, score);

		val result = new HashSet<TypedTuple<String>>();
		result.add(new DefaultTypedTuple<String>(restaurantCode, score));
		return new Response(key, result);

	}

	@GetMapping(value = "/leaderboard", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Response> seeLeaderboard() {
		return getRanking();
	}

	@GetMapping(value = "/leaderboard/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response seeLeaderboard(@PathVariable("type") String type) {

		if (!redisTemplate.hasKey(type)) {
			throw new NotFoundException();
		}

		return new Response(type, redisTemplate.opsForZSet().reverseRangeWithScores(type, 0, -1));

	}

	private List<Response> getRanking() {
		val result = new ArrayList<Response>();

		for (Cities city : Cities.values()) {
			for (Types type : Types.values()) {
				val key = city.code + GlobalConstants.COLON + type.code;
				result.add(new Response(key,
										redisTemplate
												.opsForZSet()
												.reverseRangeWithScores(key, 0, -1)));
			}
		}

		return result;
	}



}
