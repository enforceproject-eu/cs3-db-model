package org.n52.project.enforce.cs3.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.n52.project.enforce.cs3.db.repository.Cs3DataRepository;
import org.n52.project.enforce.cs3.utils.Cs3Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Cs3DataService {
    
    private Cs3DataRepository cs3DataRepository;
    
    private Cs3Utils utils;

    public Cs3DataService(Cs3DataRepository cs3DataRepository, Cs3Utils utils) {
        this.cs3DataRepository = cs3DataRepository;
        this.utils = utils;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/cs3_data",
            consumes = { "application/enforce-cs3-data" })
    public ResponseEntity<Serializable> postCs3PlayasData(@RequestBody(
            required = true) InputStream cs3Data) {
        try {
            utils.readExcelFile(cs3Data);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/cs3_data",
            produces = { "application/json" })
    public ResponseEntity<Serializable> getCs4PlayasData() {
        try {
            return ResponseEntity.ok(cs3DataRepository.getGeoJson());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
