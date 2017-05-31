package prototypes.redis.enums;

import java.util.HashMap;
import java.util.Map;
import prototypes.redis.constants.GlobalConstants;

/**
 * @author fangda.wang
 */
public enum Restaurants {

	RYUGIN(Cities.TOKYO.code + GlobalConstants.COLON + Types.JAPANESE_FOOD.code, "RyuGin", 5.0),
	LeParc(Cities.TOKYO.code + GlobalConstants.COLON + Types.DIM_SUM.code, "LeParc", 3.7),
	ParadiseDynasty(Cities.TOKYO.code + GlobalConstants.COLON + Types.DIM_SUM.code, "ParadiseDynasty", 3.8),
	DinTaiFung(Cities.TOKYO.code + GlobalConstants.COLON + Types.DIM_SUM.code, "DinTaiFung", 3.8);

	private static class HOLDER {
		private static final Map<String, String> MAPPER = new HashMap<>();
	}

	public final String type;
	public final String code;
	public final double score;

	Restaurants(String type, String code, double score) {
		this.type = type;
		this.code = code;
		this.score = score;
		HOLDER.MAPPER.put(code, type);
	}

	public static String getType(final String restaurantCode) {
		return HOLDER.MAPPER.get(restaurantCode);
	}

}
