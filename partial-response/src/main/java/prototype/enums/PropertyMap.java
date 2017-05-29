package prototype.enums;
import com.google.common.collect.ImmutableSet;
import prototype.bean.FirstLevelObjectProperty;
import prototype.bean.Response;
import prototype.bean.SecondLevelObjectProperty;
import prototype.bean.ThirdLevelObjectProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author fangda.wang
 */
public enum PropertyMap {

	RESPONSE(true, Response.ID, "nation", "info"),
	INFO(FirstLevelObjectProperty.ID, "state", "details"),
	DETAILS(SecondLevelObjectProperty.ID, "type", "number", "subdetails"),
	SUBDETAILS(ThirdLevelObjectProperty.ID, "code", "name", "price");

	private static final class Holder {
		private static final Map<String, Set<String>> FIRST_LEVEL_MAP = new HashMap<>();
		private static final Map<String, Set<String>> NON_FIRST_LEVEL_MAP = new HashMap<>();
	}

	PropertyMap(String name, String... properties) {
		Holder.NON_FIRST_LEVEL_MAP.put(name, ImmutableSet.<String>builder().add(properties).build());
	}

	PropertyMap(boolean isFirstLevel, String name, String... properties) {
		Holder.FIRST_LEVEL_MAP.put(name, ImmutableSet.<String>builder().add(properties).build());
	}

	public static Map<String, Set<String>> getNonFirstLevel() {
		return Holder.NON_FIRST_LEVEL_MAP;
	}

	public static Map<String, Set<String>> getFirstLevel() {
		return Holder.FIRST_LEVEL_MAP;
	}

}
