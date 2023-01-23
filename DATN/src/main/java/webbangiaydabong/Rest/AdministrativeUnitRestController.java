package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.AdministrativeUnitDto;
import webbangiaydabong.service.AdministrativeUnitService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/administrativeUnit")
public class AdministrativeUnitRestController {
    @Autowired
    AdministrativeUnitService administrativeUnitService;

    @PostMapping ("tinhhuyenxa")
    public List<AdministrativeUnitDto> get(@RequestBody AdministrativeUnitDto dto) {
        Long parent_id=dto.getParentId();
        List<AdministrativeUnitDto> result = administrativeUnitService.tinhhuyenxa(parent_id);
        return result;
    }
}
