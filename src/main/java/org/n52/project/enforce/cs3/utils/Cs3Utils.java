package org.n52.project.enforce.cs3.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.n52.project.enforce.cs3.db.model.Cs3Data;
import org.n52.project.enforce.cs3.db.repository.Cs3DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Cs3Utils {

    private Cs3DataRepository cs3DataRepository;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

    ZoneId zoneIdEuropeAthens = ZoneId.of("Europe/Athens");
    
    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    private static Logger LOG = LoggerFactory.getLogger(Cs3Utils.class);

    public Cs3Utils(Cs3DataRepository cs3DataRepository) {
        this.cs3DataRepository = cs3DataRepository;
    }

    public boolean ckeckIdIsInDb(int fictionaryId) {
        Optional<Cs3Data> possibleExistingDbData = cs3DataRepository.findByFictionaryId(fictionaryId);
        if (possibleExistingDbData.isPresent()) {
            return true;
        }
        return false;
    }
    
    public void readExcelFile(InputStream inputstream) throws IOException {
        File tmpExcelFile = File.createTempFile("excel", ".xlsx");
        IOUtils.copy(inputstream, new FileOutputStream(tmpExcelFile));
        Workbook workbook = WorkbookFactory.create(tmpExcelFile, "land!clearing");
        Sheet sheet = workbook.getSheetAt(0);
        Row firstRow = sheet.getRow(0);
        int rowCount = sheet.getLastRowNum();
        int columnCount = firstRow.getLastCellNum();
        Cs3Data data = null;
        Row row = null;
        Cell cell = null;
        for (int i = 1; i <= rowCount; i++) {
            row = sheet.getRow(i);
            int id = (int) row.getCell(0).getNumericCellValue();
            if (ckeckIdIsInDb(id)) {
                //TODO update data
                LOG.info(String.format("Data with fictionary id %d added already exists.", id));
                continue;
            }
            data = new Cs3Data();
            data.setFictionary(id);
            for (int k = 1; k < columnCount; k++) {
                cell = row.getCell(k);
                switch (k) {
                case 1:
                    data.setDateOfReport(LocalDate.ofInstant(cell.getDateCellValue().toInstant(), zoneIdEuropeAthens));
                    break;
                case 2:
                    data.setDescription(cell.getStringCellValue());
                    break;
                case 3:
                    data.setCoords(this.createPoint(cell.getStringCellValue()));
                    break;
                case 4:
                    data.setMunicipality(cell.getStringCellValue());
                    break;
                case 5:
                    data.setResponses(cell.getStringCellValue());
                    break;
                default:
                    break;
                }
            }
            cs3DataRepository.saveAndFlush(data);
            LOG.info(String.format("Data with fictionary id %d added to cs3 data.", data.getFictionary()));
        }
        workbook.close();
    }

    private Point createPoint(String pointAsString) {
        if (pointAsString != null) {
            String[] coordinateArray = pointAsString.split(",");
            if (coordinateArray.length == 2) {
                String latStrg = coordinateArray[1];
                String lngStrg = coordinateArray[0];
                if ((latStrg != null && !latStrg.isEmpty()) && (lngStrg != null && !lngStrg.isEmpty())) {
                    double lat = Double.parseDouble(latStrg);
                    double lng = Double.parseDouble(lngStrg);
                    return geometryFactory.createPoint(new Coordinate(lat, lng));
                }
            }
        }
        return geometryFactory.createPoint(new Coordinate(0, 0));
    }

}
