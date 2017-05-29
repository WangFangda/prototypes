package prototype.processing;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import prototype.exception.BadParameterException;

/**
 * @author fangda.wang
 */
@Slf4j
@Service
public class FieldParser {

	private static enum Delimiter {
		COMMMA(','), LEFT_BRACE('('), RIGHT_BRACE(')');
		public final char code;
		Delimiter(char code) {
			this.code = code;
		}
	}

	private static enum InvalidCharacter {
		SPACE("\\s+");
		public final String code;
		InvalidCharacter(String code) {
			this.code = code;
		}
		public static String sanitize(String input) {
			String result = new String(input);
			for (InvalidCharacter invalid: InvalidCharacter.values())
				result = result.replaceAll(invalid.code, StringUtils.EMPTY);
			return result;
		}
	}

	/**
	 * Extracts out the parent-children structure.
	 *
	 * For example:
	 * <pre>
	 *     given a string "a,b(c(d,e),f),g",
	 *     the output is {a=[], b=[c, f], c=[d, e], g=[]}
	 * </>
	 *
	 * @param originalFields a {@code String} containing wanted properties
	 * @return a {@code Map} containing properties (as key) and their child properties (as value)
	 * @throws BadParameterException
	 */
	public static Map<String, Set<String>> apply(final String originalFields) throws BadParameterException {

		if (StringUtils.isEmpty(originalFields)) return new HashMap<>();

		val fields = InvalidCharacter.sanitize(originalFields);

		Stack<Character> delimiterStack = new Stack<>();
		Stack<String> fieldStack = new Stack<>();

		Map<String, Set<String>> result = new HashMap<>();

		char[] allFields = fields.toCharArray();
		StringBuilder field = new StringBuilder(); // current field

		for (int index = 0; index < allFields.length; index++) {

			if (Delimiter.COMMMA.code == allFields[index]) {
				val fieldName = field.toString();
				if (StringUtils.isEmpty(fieldName)) {
					/* case: ), */
					continue;
				}
				field = new StringBuilder();
				if (!fieldStack.empty()) {
					val key = fieldStack.peek();
					result.get(key).add(fieldName);
				} else {
					result.put(fieldName, new HashSet<>());
				}
			} else if (Delimiter.LEFT_BRACE.code == allFields[index]) {
				/* case: a( */
				val fieldName = field.toString();
				field = new StringBuilder();
				if (!fieldStack.empty()) {
					val key = fieldStack.peek();
					if (StringUtils.isNotEmpty(fieldName))
						result.get(key).add(fieldName);
				}
				delimiterStack.push(Delimiter.LEFT_BRACE.code);
				fieldStack.push(fieldName);
				result.put(fieldName, new HashSet<>());
			} else if (Delimiter.RIGHT_BRACE.code == allFields[index]) {
				/* case: a) */
				val fieldName = field.toString();
				field = new StringBuilder();
				if (!delimiterStack.empty() && !fieldStack.empty()) {
					delimiterStack.pop();
					val key = fieldStack.pop();
					if (StringUtils.isNotEmpty(fieldName))
						result.get(key).add(fieldName);
				} else {
					throw new BadParameterException("Either '(' or ')' is missing.");
				}
			} else {
				field.append(allFields[index]);
			}
		}

		if (StringUtils.isNotEmpty(field.toString()))
			result.put(field.toString(), new HashSet<>());

		return result;
	}

}
