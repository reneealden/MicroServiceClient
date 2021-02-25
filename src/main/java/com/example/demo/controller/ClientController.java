package com.example.demo.controller;
 
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.ClientRequest;
import com.example.demo.response.OperationsClientResponse;
import com.example.demo.response.ResultResponse;
import com.example.demo.services.ClientService;
 
@RestController
@RequestMapping("/client")
public class ClientController {
	
	private final ClientService clientService;
	
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@PostMapping(value = "")
	public ResponseEntity<ResultResponse> createClient(@RequestBody ClientRequest request) {
		ResultResponse result = clientService.createClient(request);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> getAllClients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Map<String, Object> response = clientService.filterClients(page, size);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("kpi")
	public ResponseEntity<OperationsClientResponse> getKpiClients() {
		OperationsClientResponse result = clientService.getOperations();
		return ResponseEntity.ok(result);
	}
}
