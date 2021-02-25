package com.example.demo.services;
 
import java.util.Map;

import com.example.demo.request.ClientRequest;
import com.example.demo.response.OperationsClientResponse;
import com.example.demo.response.ResultResponse;

public interface ClientService {
	ResultResponse createClient(ClientRequest request);
	Map<String, Object> filterClients(int page, int size);
	OperationsClientResponse getOperations();
}
