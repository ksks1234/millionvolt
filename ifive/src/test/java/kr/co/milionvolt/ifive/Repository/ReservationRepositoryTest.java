package kr.co.milionvolt.ifive.Repository;

import kr.co.milionvolt.ifive.domain.reservation.ReservationDTO;
import kr.co.milionvolt.ifive.entity.ReservationRedis;
import kr.co.milionvolt.ifive.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;


    @Test
    public void save(){
        ReservationRedis dto = new ReservationRedis();
//        Timestamp timestamp = Timestamp.valueOf("2024-12-11 12:00:00");
//        Timestamp timestamp1 = Timestamp.valueOf("2024-12-11 13:00:00");
        LocalDateTime localDateTime = LocalDateTime.of(2024, 12,11,13,10,1);
        LocalDateTime localDateTime2 = LocalDateTime.of(2024, 12,11,13,10,2);

        dto.setReservationId(3);
        dto.setStartTime(localDateTime);
        dto.setEndTime(localDateTime2);
        dto.setUserId(2);

        reservationRepository.save(dto);
    }

    @Test
    public void findByuserId(){
        List<ReservationRedis> list = (List<ReservationRedis>) reservationRepository.findAll();
        list.stream().forEach(System.out::println);

//       List<Timestamp> startTime =  allReservations.stream()
//            .filter(reservation -> reservation.getUserId() == 1) // userId로 필터링
//                .map(ReservationRedis::getStartTime) // startTime 추출
//                .toList();
//        startTime.stream().forEach(System.out::println);


    }

}
