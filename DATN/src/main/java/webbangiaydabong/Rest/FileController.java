package webbangiaydabong.Rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import webbangiaydabong.service.UploadService;

@RestController
@RequestMapping("/api/public/file/")
public class FileController {
	@Autowired
	UploadService uploadService;
	
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
//	@PostMapping(value = "product")
//    public HttpStatus productUpload(@RequestParam("file") MultipartFile multipartFile){
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        try {
//        	System.out.println("alo");
//            uploadService.save("imageproduct",fileName, multipartFile);
//            return HttpStatus.OK;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return HttpStatus.CONFLICT;
//        }
//    } 
}
