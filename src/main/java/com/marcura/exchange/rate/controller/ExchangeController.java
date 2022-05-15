package com.marcura.exchange.rate.controller;

import com.marcura.exchange.rate.api.exceptions.ExchangeRateNotFoundException;
import com.marcura.exchange.rate.api.exceptions.ParameterException;
import com.marcura.exchange.rate.api.responses.CalculatedExchangeRateResponse;
import com.marcura.exchange.rate.api.responses.LatestExchangeRateResponse;
import com.marcura.exchange.rate.service.exchange.ExchangeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by ruhancoetzee on May-2022.
 */


/**
 * Exchange API controller.
 * API GET(/exchange) calculateExchangeRates will calculate the exchange rate between two given currencies by date.
 * Date is a non required parameter. If parameter date is not included calculation will default to today's date.
 *
 * API PUT(/exchange) will capture the latest exchange rates from external api.
 */
@Api(value = "ExchangeController")
@RestController
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @ApiOperation(value = "Calculate Exchange Rate For Given From And To Currency By Date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Calculated Exchange Rate.", responseHeaders = {@ResponseHeader(response = CalculatedExchangeRateResponse.class)}),
            @ApiResponse(code = 400, message = "Parameter Exception. Bad Request.", responseHeaders = {@ResponseHeader(response = ParameterException.class)}),
            @ApiResponse(code = 404, message = "Exchange Rate Not Found.", responseHeaders = {@ResponseHeader(response = ExchangeRateNotFoundException.class)})})
    @RequestMapping(value = "/exchange", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CalculatedExchangeRateResponse> calculateExchangeRates(
            @RequestParam(value = "from", defaultValue = "USD") String from,
            @RequestParam(value = "to", defaultValue = "ZAR") String to,
            @RequestParam(value = "date", required = false) String date) {
        return ResponseEntity.ok(new CalculatedExchangeRateResponse(from, to, exchangeService.calculateExchangeRate(from, to, date)));
    }

    @ApiOperation(value = "Capture Latest Exchange Rates From External API.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Captured Latest Exchange Rates.", responseHeaders = {@ResponseHeader(response = LatestExchangeRateResponse.class)})})
    @RequestMapping(value = "/exchange", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LatestExchangeRateResponse> saveOrUpdateExchangeRates() {
        exchangeService.saveLatestExchangeRates(exchangeService.getLatestExchangeRates());
        return ResponseEntity.ok(new LatestExchangeRateResponse("Successfully Pulled Latest Exchange Rates", new Date()));
    }
}