package webbangiaydabong.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.ProductDTO;

public interface UploadService {
File save(MultipartFile file, String folder);
List<ProductDTO>importProducttoExcel(MultipartFile file);
List<ProductDTO> savelist(List<ProductDTO> list);
}
