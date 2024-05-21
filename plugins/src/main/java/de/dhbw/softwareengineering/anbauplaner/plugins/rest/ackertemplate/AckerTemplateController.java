package de.dhbw.softwareengineering.anbauplaner.plugins.rest.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.adapters.ackertemplate.representations.AckerTemplateRequestDTO;
import de.dhbw.softwareengineering.anbauplaner.application.ackertemplate.AckerTemplateService;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
public class AckerTemplateController {

	private AckerTemplateService ackerTemplateService;

	@Autowired
	public AckerTemplateController(AckerTemplateService ackerTemplateService) {
		this.ackerTemplateService = ackerTemplateService;
	}

	@GetMapping("/ackers")
	public List<AckerTemplate> getAckerTemplates() {
		List<AckerTemplate> ackers = ackerTemplateService.findAllAckerTemplates();
		return ackers;
	}

	@GetMapping("/acker/{ackerId}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "AckerTemplate not found")
	})
	public AckerTemplate getAckerTemplateById(@PathVariable("ackerId") UUID ackerId) {
		AckerTemplate acker = ackerTemplateService.findAckerTemplateById(ackerId);
		return acker;
	}

	@PostMapping(value = "/acker", consumes = {"application/json"}, produces = {"application/json"})
	public @ResponseBody AckerTemplate createAckerTemplate(@RequestBody AckerTemplateRequestDTO request) {
		return ackerTemplateService.createAcker(request.getName(), request.getXLength(), request.getYLength(), request.getX(), request.getY());
	}

	@PutMapping(value = "/acker/{ackerId}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "AckerTemplate not found")
	})
	public @ResponseBody AckerTemplate changeAckerDimensions(@PathVariable("ackerId") UUID ackerId, @RequestBody AckerTemplateRequestDTO request) {
		AckerTemplate acker = ackerTemplateService.changeAckerDimensions(ackerId, request.getXLength(), request. getYLength());
		return acker;
	}

	@DeleteMapping("/acker/{ackerId}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Acker successfully deleted"),
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "AckerTemplate not found")
	})
	public ResponseEntity<Void> deleteAckerTemplate(@PathVariable("ackerId") UUID ackerId) {
		try {
			ackerTemplateService.deleteAckerTemplateById(ackerId);
			return ResponseEntity.noContent().build();
		} catch(EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
