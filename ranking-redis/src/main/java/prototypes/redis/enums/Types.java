package prototypes.redis.enums;

/**
 * @author fangda.wang
 */
public enum Types {
	DIM_SUM("dim sum"),
	JAPANESE_FOOD("japanese food");
	public final String code;
	Types(String code) {
		this.code = code;
	}

}
