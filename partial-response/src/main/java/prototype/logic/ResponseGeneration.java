package prototype.logic;

import lombok.val;
import org.springframework.stereotype.Service;
import prototype.bean.FirstLevelObjectProperty;
import prototype.bean.Response;
import prototype.bean.SecondLevelObjectProperty;
import prototype.bean.ThirdLevelObjectProperty;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author fangda.wang
 */
@Service
public class ResponseGeneration {

	public Response make() {

		val tomato = new ThirdLevelObjectProperty("1", "tomato", BigDecimal.valueOf(100.0));
		val potato = new ThirdLevelObjectProperty("2", "potato", BigDecimal.valueOf(55.0));
		val details = new SecondLevelObjectProperty("vegetable",
				Integer.valueOf(0),
				Arrays.asList(tomato, potato));
		val info = new FirstLevelObjectProperty("Tokyo", Collections.singletonList(details));
		return new Response("Japan", info);

	}
}
