package com.example.demo.services.impl;

import java.text.MessageFormat; 
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.example.demo.constants.ErrorMessage;
import com.example.demo.controller.entities.ClientEntity;
import com.example.demo.exceptions.IncorrectFieldFormatException;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.request.ClientRequest;
import com.example.demo.response.ClientResponse;
import com.example.demo.response.OperationsClientResponse;
import com.example.demo.response.ResultResponse;
import com.example.demo.services.ClientService; 

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	public ResultResponse createClient(ClientRequest request) {
		StopWatch stopWatch = new StopWatch();
        stopWatch.start();
		log.info("[Class:ClientServiceImpl Method:createClient] Starting {}. ", request.getName());
		ClientEntity clientEntity = new ClientEntity();		
		clientEntity.setName(request.getName());
		clientEntity.setLastName(request.getLastname());
		try {
			clientEntity.setBirthDate(convertToDate(request.getBirthdate()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncorrectFieldFormatException(MessageFormat.format(ErrorMessage.INCORRECTFIELDFORMAT, "BirthtDate"));
		}
		clientRepository.saveAndFlush(clientEntity);
		
		stopWatch.stop();
		log.info("[Class:ClientServiceImpl Method:createClient] Finish For User "+ request.getName() + " Time {} ms.", stopWatch.getTotalTimeMillis());
		return new ResultResponse(clientEntity.getClientId());
	}
	
	public Map<String, Object> filterClients(int page, int size) {
		log.info("[Class:DirectoryServiceImpl filterDirectories] page. " + page);
		log.info("[Class:DirectoryServiceImpl filterDirectories] size. " + size);
	    Pageable paging = PageRequest.of(page, size);
	    Page<ClientEntity> pageTuts;
	    pageTuts = clientRepository.findByOrderByCreationDateAsc(paging);
	    ModelMapper modelMapper = new ModelMapper(); 

	    List<ClientResponse> resultClients = Arrays.asList(modelMapper.map(pageTuts.getContent(), ClientResponse[].class));
	    Map<String, Object> response = new HashMap<>();
	    response.put("clients", resultClients);
	    response.put("currentPage", pageTuts.getNumber());
	    response.put("totalItems", pageTuts.getTotalElements());
	    response.put("totalPages", pageTuts.getTotalPages());
	    
	    return response;
	}
	
	public OperationsClientResponse getOperations() {
		List<ClientEntity> lstClient = clientRepository.findAll();
		return new OperationsClientResponse(getAverage(lstClient), getStandardDeviation(lstClient));
	}
	
	private double getAverage(List<ClientEntity> lstClient) {
		double acum = lstClient.stream().mapToInt(i -> Integer.parseInt(i.getAge())).sum();
		acum = !lstClient.isEmpty() ? acum/lstClient.size() : 0;
		return acum;
	}
	
	private double getStandardDeviation(List<ClientEntity> lstClient) {
	    double prom, sum = 0; int n = lstClient.size();
	    prom = getAverage(lstClient);
	    sum = lstClient.stream().mapToDouble(e -> (double) Math.pow( Integer.parseInt(e.getAge()) - prom, 2)).sum();
	    return prom > 0 ? Math.sqrt(sum /(double)n) : 0;
	}
	
	private Date convertToDate(String dateRequest) throws Exception {
		Date birthDateParse = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		log.info("[Class:ClientServiceImpl Method:convertToDate] Starting {}. ", dateRequest);
		if (!dateRequest.isEmpty()) {
			birthDateParse = dateFormat.parse(dateRequest);
		}
		log.info("[Class:ClientServiceImpl Method:convertToDate] birthDateParse {}. ", String.valueOf(birthDateParse));
		return birthDateParse;
	}
}
