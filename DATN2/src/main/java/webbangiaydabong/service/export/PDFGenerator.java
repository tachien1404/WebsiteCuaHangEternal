package webbangiaydabong.service.export;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;

import com.lowagie.text.pdf.PdfWriter;
import lombok.Setter;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
@Setter
public class PDFGenerator {
    private OrderDTO orderDTOList;
    public Document generate(HttpServletResponse response) throws DocumentException, IOException {
        // Create the Object of Document
        Document document = new Document(PageSize.A4);
        // get the document and write the response to output stream
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // Add Font
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Create Object of Paragraph
        Paragraph paragraph = new Paragraph("Etenal", fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        // Add to the document
        //Thêm 1 ảnh
//     Image image1 = Image.getInstance("1.jpeg");
//        document.add(new Paragraph("Image 1"));
//        document.add(image1);

        document.add(paragraph);
        document.add(new Paragraph("-------------------------------------------------------------------------------------"));
        document.add(new Paragraph("-Tu",fontTiltle));
        document.add(new Paragraph("Cửa hàng giày Etenal"));
        document.add(new Paragraph("xóm 4 Phúc Đức Sài Sơn Quốc Oai Hà Nội"));
        document.add(new Paragraph("Sdt: 0986087672"));
        document.add(new Paragraph("-------------------------------------------------------------------------------------"));
        document.add(new Paragraph("-Den",fontTiltle));

        if(orderDTOList!=null && orderDTOList.getNameinfo()!=null){
            document.add(new Paragraph(orderDTOList.getNameinfo()));
        }else{
            document.add(new Paragraph(orderDTOList.getName_acount()));
        }

        if(orderDTOList!=null && orderDTOList.getAddressinfo()!=null){
            document.add(new Paragraph(orderDTOList.getAddressinfo()));
        }else{
            document.add(new Paragraph(orderDTOList.getAddress()));
        }
        if(orderDTOList!=null && orderDTOList.getSdtinfo()!=null){
            document.add(new Paragraph(orderDTOList.getSdtinfo()));
        }else{
            document.add(new Paragraph(orderDTOList.getSdt()));
        }
        document.add(new Paragraph("-------------------------------------------------------------------------------------"));
        document.add(new Paragraph("-Noi dung hàng",fontTiltle));
       for(OrderDetailDTO x: orderDTOList.getLstOrderDetailDTOS()){
           document.add(new Paragraph("-"+x.getProductName()+"."+x.getQuantity()));
       }
        document.add(new Paragraph("-------------------------------------------------------------------------------------"));
        document.add(new Paragraph("-Tiền thu người nhận"));
        document.add(new Paragraph("        "+orderDTOList.getPrice()+"      VND",fontTiltle));
        document.add(new Paragraph("Kiem tra hàng trước khi thanh toán"));
        document.add(new Paragraph("-Chi dan giao hàng:",fontTiltle));
        document.add(new Paragraph("-Không đóng kiểm"));
        document.add(new Paragraph("-Chuyển hoàn sau 3 lần phát"));
        document.add(new Paragraph("-Lưu khó tối đa 5 ngày"));

//        PdfPTable table = new PdfPTable(2);
//        table.setWidthPercentage(100f);
//        table.setWidths(new int[] { 1, 2 });
//        table.setSpacingBefore(2);
//        // Create Table Header
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(Color.MAGENTA);
//        cell.setPadding(5);
//        // Add Font
//        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        font.setColor(Color.WHITE);
//        cell.setPhrase(new Phrase("Từ", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Đến", font));
//        table.addCell(cell);
//        for (OrderDetailDTO orderDetailDTO: orderDTOList.getLstOrderDetailDTOS()) {
//
//        }
//        // Add table to document
//        document.add(table);
        document.close();
        return document;
    }
}
