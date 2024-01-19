package com.example.demo.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.demo.request.DemoRequest;
import com.example.demo.response.DemoResponse;

import jakarta.annotation.PostConstruct;

@Service
public class DemoServiceImpl implements DemoService{

    private DemoResponse response = new DemoResponse();

    DecimalFormat df;

    @PostConstruct
    public void init(){
        Locale.setDefault(new Locale("es","CL"));
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setDecimalSeparator('.');
        df = new DecimalFormat("###,###,###", otherSymbols);
    }

    @Override
    public DemoResponse demoFunction(DemoRequest body) {
        if (body.getOk()){

            response.setCode("00");
            response.setMessage("OPERACION EXITOSA");
            response.setYourNumber("$"+df.format(Double.parseDouble(body.getNumber())));

        } else {

            response.setCode("16");
            response.setMessage("ERROR");
            response.setYourNumber(null);

        }

        
        return response;
    }
    
}
