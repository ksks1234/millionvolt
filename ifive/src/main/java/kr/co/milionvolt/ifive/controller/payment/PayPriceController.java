package kr.co.milionvolt.ifive.controller.payment;

import kr.co.milionvolt.ifive.domain.payment.PayPriceDTO;
import kr.co.milionvolt.ifive.mapper.PayPriceMapper;
import kr.co.milionvolt.ifive.service.payment.PayPriceService;
import kr.co.milionvolt.ifive.service.payment.PayPriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PayPriceController {

    @Autowired
    private PayPriceServiceImpl payPriceServiceImpl;  // 인터페이스 타입으로 주입 받기

    @Autowired
    private PayPriceMapper payPriceMapper;

    @PostMapping("/api/v1/payment/save/{imp_uid}")
    public ResponseEntity<String> savePayment(@PathVariable String imp_uid, @RequestBody PayPriceDTO payPriceDTO ) {
        try {
            System.out.println(imp_uid);
            // 결제 검증 서비스 호출
            if (payPriceServiceImpl.verifyPayment(imp_uid)){

                payPriceDTO.setImpUid(imp_uid);

                // 결제 정보 DB에 삽입
                payPriceServiceImpl.savePayPrice(payPriceDTO);

                // 성공적인 응답 반환 (HTTP 상태 200)
                return ResponseEntity.ok("결제에 성공했습니다.");
            } else {

                // 결제 검증 실패 시 400 Bad Request 반환
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 검증에 실패했습니다.");
            }
        } catch (Exception e) {

            // 예외 발생 시, 로그를 통해 상세한 에러를 기록
            e.printStackTrace();

            // 서버 내부 오류 (500 Internal Server Error) 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제에 문제가 발생했습니다.");
        }
    }
}