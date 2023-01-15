package webbangiaydabong.Rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import webbangiaydabong.dto.AdministrativeUnitDto;
import webbangiaydabong.dto.functiondto.AdministrativeUnitImportExcel;
import webbangiaydabong.service.AdministrativeUnitService;
import webbangiaydabong.service.UploadService;
import webbangiaydabong.utils.ImportExportExcelUtil;

@RestController
@RequestMapping("/api/public/file/")
public class FileController {
	@Autowired
	UploadService uploadService;
	@Autowired
    AdministrativeUnitService administrativeUnitService;
	@PostMapping(value = "account")
    public HttpStatus userUpload(@RequestParam("file") MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            uploadService.save("user",fileName, multipartFile);
            return HttpStatus.OK;
        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.CONFLICT;
        }
    } 
	@PostMapping(value = "product")
    public HttpStatus productUpload(@RequestParam("file") MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
        	System.out.println("alo");
            uploadService.save("imageproduct",fileName, multipartFile);
            return HttpStatus.OK;
        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.CONFLICT;
        }
    }
    @RequestMapping(value = "importUnitFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> importUnitFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(uploadfile.getBytes());
            List<AdministrativeUnitImportExcel> list = ImportExportExcelUtil.importAdministrativeUnitFromInputStream(bis);
            if(list != null && list.size()>0) {
                List<AdministrativeUnitDto> listData = administrativeUnitService.importExcel(list);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
