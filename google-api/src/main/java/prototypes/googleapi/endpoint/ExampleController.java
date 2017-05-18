package prototypes.googleapi.endpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prototypes.googleapi.logic.GoogleGeocodeCaller;
import prototypes.googleapi.logic.GooglePlaceDetailsCaller;
import prototypes.googleapi.model.geocode.Place;
import prototypes.googleapi.model.placedetails.PlaceDetailsApiResponse;
import prototypes.googleapi.model.response.PlaceDetails;
import prototypes.googleapi.util.ModelMappingUtils;
import prototypes.googleapi.util.ResponseUtils;
/**
 * @author fangda.wang
 */
@RestController
public class ExampleController {

	@Autowired
	private GoogleGeocodeCaller geocodeCaller;

	@Autowired
	private GooglePlaceDetailsCaller placeDetailsCaller;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlaceDetails> exec(@RequestParam String address, HttpServletResponse httpResponse) {

		/* validation */
		if (StringUtils.isEmpty(address)) {
			httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return Collections.emptyList();
		}

		val geocodeResBody = geocodeCaller.exec(address);

		/* early return */
		if (ResponseUtils.isEmpty(geocodeResBody)) {
			httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return Collections.emptyList();
		}

		/* parallelism */
		val allAsyncs = geocodeResBody
								.getResults()
								.stream()
								.map(place -> CompletableFuture.supplyAsync(() -> toPlaceDetailsApiResponse(place)))
								.toArray(CompletableFuture[]::new);
		val allDone = CompletableFuture.allOf(allAsyncs);

		val places = geocodeResBody
							 .getResults()
							 .stream()
							 .collect(Collectors.toMap(Place::getPlace_id, place -> place));

		List<PlaceDetails> result = null;
		try {
			result = allDone
							 .thenApply(v -> {
								 val data = new ArrayList<PlaceDetails>();
								 for (CompletableFuture<PlaceDetailsApiResponse> async : allAsyncs) {
									 try {
										 val details = async.get();
										 val place = places.get(details
																		.getResult()
																		.getPlace_id());
										 val combined = ModelMappingUtils.exec(place, details.getResult());
										 data.add(combined);
									 } catch (InterruptedException | ExecutionException e) {
										 e.printStackTrace();
									 }
								 }
								 return data;
							 })
							 .get();
		} catch (InterruptedException | ExecutionException e) {
			httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return result;

	}

	private PlaceDetailsApiResponse toPlaceDetailsApiResponse(final Place place) {
		return StringUtils.isEmpty(place.getPlace_id())
			   ? new PlaceDetailsApiResponse()
			   : placeDetailsCaller.exec(place.getPlace_id());
	}


}
