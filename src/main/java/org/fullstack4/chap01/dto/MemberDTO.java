package org.fullstack4.chap01.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String user_id;
    private String name;
    private String pwd;
    private String jumin;
    private String addr1;
    private String addr2;
    private String birthday;
    private String job_code;
    private int mileage;
    private String user_state;
    private LocalDate reg_date;
    private LocalDate leave_date;
    private LocalDate pwd_change_date;
}
