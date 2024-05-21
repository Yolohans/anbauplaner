package de.dhbw.softwareengineering.anbauplaner.plugins.rest.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.adapters.ackertemplate.representations.TunnelTemplateRequestDTO;
import de.dhbw.softwareengineering.anbauplaner.adapters.shape.representations.PointDTO;
import de.dhbw.softwareengineering.anbauplaner.application.ackertemplate.AckerTemplateService;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TunnelTemplateController {

	private AckerTemplateService ackerTemplateService;

	@Autowired
	public TunnelTemplateController(AckerTemplateService ackerTemplateService) {
		this.ackerTemplateService = ackerTemplateService;
	}

	@GetMapping("/acker/{ackerId}/tunnels")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "Entity not found")
	})
	public List<TunnelTemplate> getTunnelTemplatesByAckerId(@PathVariable("ackerId") UUID ackerId) {
		List<TunnelTemplate> tunnels = ackerTemplateService.findTunnelTemplatesByAckerId(ackerId);
		return tunnels;
	}

	@GetMapping("/acker/tunnel/{tunnelId}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "AckerTemplate not found")
	})
	public TunnelTemplate getTunnelTemplateById(@PathVariable("tunnelId") UUID tunnelId) {
		TunnelTemplate tunnel = ackerTemplateService.findTunnelTemplateById(tunnelId);
		return tunnel;
	}

	@PostMapping(value = "/acker/{ackerId}/tunnel", consumes = {"application/json"}, produces = {"application/json"})
	public @ResponseBody TunnelTemplate createTunnel(@PathVariable("ackerId") UUID ackerId, @RequestBody TunnelTemplateRequestDTO request) {
		return ackerTemplateService.createTunnel(ackerId, request.getName(), request.getXLength(), request.getYLength(), request.getX(), request.getY());
	}

	@DeleteMapping("/acker/tunnel/{tunnelId}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Tunnel successfully deleted"),
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "Tunnel not found")
	})
	public ResponseEntity<Void> deleteTunnelTemplateById(@PathVariable("tunnelId") UUID tunnelId, @RequestBody Boolean keepBeete) {
		try {
			ackerTemplateService.deleteTunnelById(tunnelId, keepBeete);
			return ResponseEntity.noContent().build();
		} catch(EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(value = "/acker/{ackerId}/tunnel/{tunnelId}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Invalid UUID format"),
			@ApiResponse(responseCode = "404", description = "Entity not found")
	})
	public @ResponseBody TunnelTemplate moveTunnelToPosition(@PathVariable("ackerId") UUID ackerId, @PathVariable("tunnelId") UUID tunnelId, @RequestBody PointDTO request) {
		TunnelTemplate tunnel = ackerTemplateService.moveTunnelToPosition(ackerId, tunnelId, request.getX(), request.getY());
		return tunnel;
	}
}
