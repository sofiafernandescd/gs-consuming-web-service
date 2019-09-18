package hello.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.CountryClient;
import hello.wsdl.Country;
import hello.wsdl.ExistingCountryResponse;
import hello.wsdl.GetCountryResponse;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {
	
	@Autowired
	private CountryClient countryClient;
	
	
	@RequestMapping(value="/{country}", method=RequestMethod.GET)
	public GetCountryResponse findCountry(@PathVariable("country") String country) {
		GetCountryResponse b = countryClient.getCountry(country);
		return b;
	}
	
	@RequestMapping(value="exist/{country}", method=RequestMethod.GET)
	public ExistingCountryResponse isCountry(@PathVariable("country") String country) {
		ExistingCountryResponse b = countryClient.existingCountry(country);
		return b;
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, HttpServletRequest request) {
		return "###EXCEÇAO CUSTOMIZADA -- >"+ex.getMessage();
		
	}
	
}