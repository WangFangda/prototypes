package prototypes.googleapi.util;
import prototypes.googleapi.model.geocode.Place;
import prototypes.googleapi.model.placedetails.Details;
import prototypes.googleapi.model.response.PlaceDetails;
/**
 * @author fangda.wang
 */
public class ModelMappingUtils {

	public static PlaceDetails exec(final Place place, final Details details) {
		PlaceDetails result = new PlaceDetails();

		if (null != place) {
			result.setFormatted_address(place.getFormatted_address());
			result.setLocation(place.getGeometry().getLocation());
			result.setTypes(place.getTypes());
		}

		if (null != details) {
			result.setName(details.getName());
			result.setInternational_phone_number(details.getInternational_phone_number());
			if (null != details.getOpening_hours())
				result.setOpening_hours(details.getOpening_hours().getWeekday_text());
		}

		return result;
	}

}
