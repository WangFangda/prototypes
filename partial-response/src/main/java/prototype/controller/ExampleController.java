package prototype.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prototype.enums.PropertyMap;
import prototype.exception.BadParameterException;
import prototype.form.InputForm;
import prototype.logic.ResponseGeneration;
import prototype.processing.FieldParser;
import prototype.processing.FilterMaker;
import prototype.processing.WriterMaker;
/**
 * @author fangda.wang
 */
@Slf4j
@RestController
public class ExampleController {

	@Autowired
	private ResponseGeneration responseGeneration;

	@Autowired
	private FieldParser fieldParser;

	@Autowired
	private FilterMaker filterMaker;

	@Autowired
	private WriterMaker writerMaker;


	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String execute(InputForm parameters) throws JsonProcessingException, BadParameterException {

		val completeResponse = responseGeneration.make();

		val wantedProperties = fieldParser.apply(parameters.getFields());
		val filters = filterMaker.apply(wantedProperties, PropertyMap.getFirstLevel(), PropertyMap.getNonFirstLevel());
		val writer = writerMaker.apply(filters);
		return writer.writeValueAsString(completeResponse);

	}
}
