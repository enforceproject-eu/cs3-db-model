package org.n52.project.enforce.cs3.api.impl.themis;

import java.io.IOException;
import java.io.Serializable;

import org.n52.project.enforce.cs3.api.ThemisApi;
import org.n52.project.enforce.cs3.db.repository.Cs3DataRepository;
import org.n52.project.enforce.cs3.utils.Cs3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-04T09:20:35.872245900+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
@Controller
@RequestMapping("${openapi.eNFORCEDataAccess.base-path:}")
public class ThemisApiController implements ThemisApi {
    
    private Cs3DataRepository cs3DataRepository;
    
    private Cs3Utils utils;

    @Autowired
    public ThemisApiController(Cs3DataRepository cs3DataRepository, Cs3Utils utils) {
        this.cs3DataRepository = cs3DataRepository;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<Serializable> addCs3ThemisDataAsBody(@Valid Resource body) {
        try {
            utils.readExcelFile(body.getInputStream());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Serializable> getCs3ThemisData() {
        try {
            return ResponseEntity.ok(cs3DataRepository.getGeoJson());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
