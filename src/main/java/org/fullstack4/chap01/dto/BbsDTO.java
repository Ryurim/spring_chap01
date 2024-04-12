package org.fullstack4.chap01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BbsDTO {

    private int idx;
    //int 범위를 넘어갈 수 있다는 것을 알고있기. long과는 정렬/순서에서 속도 차이남.
    //범위 벗어나지 않으면 int 사용하는 것이 더 좋음
    private String user_id;
    private String title;
    private String content;
    private String display_date;
    private LocalDate reg_date;
    private LocalDate modify_date;
    private int read_cnt;

}
