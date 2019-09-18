package hello;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import hello.wsdl.GetCountryRequest;
import hello.wsdl.GetCountryResponse;
import hello.wsdl.ExistingCountryRequest;
import hello.wsdl.ExistingCountryResponse;

public class CountryClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

	public GetCountryResponse getCountry(String country) {

		GetCountryRequest request = new GetCountryRequest();
		request.setName(country);

		log.info("Requesting location for " + country);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/countries", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

		return response;
	}
	
	public ExistingCountryResponse existingCountry(String country) {

		ExistingCountryRequest request = new ExistingCountryRequest();
		request.setName(country);

		log.info("Requesting location for " + country);

		ExistingCountryResponse response = (ExistingCountryResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/exist", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/ExistingCountryRequest"));

		return response;
	}

}