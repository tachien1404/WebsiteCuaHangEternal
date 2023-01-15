package webbangiaydabong.service;

import webbangiaydabong.dto.AdministrativeUnitDto;
import webbangiaydabong.dto.functiondto.AdministrativeUnitImportExcel;

import java.util.List;

public interface AdministrativeUnitService {
    List<AdministrativeUnitDto> importExcel(List<AdministrativeUnitImportExcel> dtos);
}
