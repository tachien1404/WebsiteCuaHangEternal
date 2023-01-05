package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.entity.Cart;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.entity.S_C_Details;
import webbangiaydabong.service.CartService;
import webbangiaydabong.service.S_C_DetailService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/cart")
public class CartRestController {

    @Autowired
    CartService cartService;

    @Autowired
    S_C_DetailService service;

    @GetMapping("/byUserName/{userName}")
    public ResponseEntity<ResponseObject> getAllByUserName(@PathVariable("userName") String userName){
        try{
            List<Cart> carts=cartService.findAllByUserName(userName);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK,
                     "Tìm thấy thành công",carts));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "thất bại", ""));
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Cart cart){
        System.out.println(cart);
        int quantity = 0;
        List<S_C_Details> list = service.findQuantity(cart.getProduct(), cart.getSize(), cart.getMau());
        for (S_C_Details x : list) {
            quantity += x.getQuantity();
        }
        Cart find =cartService.findBySizeColorAndUser(cart.getProduct().getId(),cart.getSize().getId(),
                cart.getMau().getId(),cart.getUserName());
        if(find!=null){
           if((find.getQuantity()+ cart.getQuantity()) > quantity){
               find.setQuantity(quantity);
               cartService.creat(find);
               return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Đã thêm vào giỏ hàng", ""));
           }
           find.setQuantity(find.getQuantity()+cart.getQuantity());
           cartService.creat(find);
           return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Đã thêm vào giỏ hàng", ""));
        }
        cartService.creat(cart);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Đã thêm vào giỏ hàng", ""));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        cartService.delete(id);
    }

    @GetMapping("/deleteAll/{userName}")
    public ResponseEntity<ResponseObject> deleteALlByUser(@PathVariable("userName") String userName){
        try {
            cartService.deleteAllByUserName(userName);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Đã xóa giỏ hàng thành công", ""));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "Xóa giỏ hàng thất bại", ""));
        }
    }
}
