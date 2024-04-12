package org.fullstack4.chap01.domain;

import lombok.*;

import java.time.LocalDate;
//@Getter
//// 이 클래스가 가지고 있는 멤버필드들의 게터 자동생성. getIdx 요렇게 첫글자는 대문자. 옵션에서 셋팅도 가능
//@Setter
@Data
@Builder
// BbsDAO 클래스에서 쓰고있는 것. 필드들을 한방에 엮어줌! 컬렉션 스트림을 이용해 내부적으로 추가한것,, vo.setIdx()보다 몇글자 덜 친다
@ToString
// 이 객체의 시리얼라이징을 자동으로 해줌. DTO에서 오버라이딩 한것처럼 롬복이 컴파일 시점에 알아서 해줄게
@NoArgsConstructor
@AllArgsConstructor
public class BbsVO {
    private int idx;
    private String user_id;
    private String title;
    private String content;
    private String display_date;
    private LocalDate reg_date;
    private LocalDate modify_date;
    private int read_cnt;
}
