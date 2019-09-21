package com.rollingstone.prescriptioncheckout.controller;

import com.rollingstone.prescriptioncheckout.domain.PrescriptionCheckout;
import com.rollingstone.prescriptioncheckout.event.PrescriptionCheckoutEvent;
import com.rollingstone.prescriptioncheckout.exceptions.HTTP400Exception;
import com.rollingstone.prescriptioncheckout.exceptions.HTTP404Exception;
import com.rollingstone.prescriptioncheckout.service.PrescriptionCheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class PrescriptionCheckoutController extends AbstractController {

    private PrescriptionCheckoutService  prescriptionCheckoutService;

    public PrescriptionCheckoutController(PrescriptionCheckoutService prescriptionCheckoutService) {
        this.prescriptionCheckoutService = prescriptionCheckoutService;
    }

    /*---Add new PrescriptionCheckout---*/
    @PostMapping("/checkoutprescriptions")
    public ResponseEntity<?> createProduct(@RequestBody PrescriptionCheckout prescriptionCheckout) {
        try {

            RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = "http://localhost:"+7070+"/checkoutprescriptions/";
            URI uri = new URI(baseUrl);

            Thread.sleep(1000);

            ResponseEntity<String> result = restTemplate.postForEntity(uri, prescriptionCheckout, String.class);


            return ResponseEntity.ok().body("New PrescriptionCheckout has been saved with ID and we called the legacy app:" + prescriptionCheckout.getID());
        }
        catch(Exception e) {
            throw new HTTP400Exception("The Request did not have proper data", new Date(), e);
        }

    }



}
