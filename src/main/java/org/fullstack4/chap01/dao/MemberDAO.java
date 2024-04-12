package org.fullstack4.chap01.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
public class MemberDAO {
    public MemberVO login(String user_id, String pwd) throws Exception{
        MemberVO vo = new MemberVO();
        String sql = "SELECT user_id, NAME, pwd, jumin, addr1, addr2, birthday, job_code, mileage, user_state, reg_date, leave_date, pwd_change_date " +
                " FROM tbl_member " +
                " WHERE USER_id = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user_id);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            if (rs.getString("pwd").equals(pwd)) {
                vo = MemberVO.builder()
                        .user_id(rs.getString("user_id"))
                        .name(rs.getString("name"))
                        .pwd(rs.getString("pwd"))
                        .jumin(rs.getString("jumin"))
                        .addr1(rs.getString("addr1"))
                        .addr2(rs.getString("addr2"))
                        .birthday(rs.getString("birthday"))
                        .job_code(rs.getString("job_code"))
                        .mileage(rs.getInt("mileage"))
                        .user_state(rs.getString("user_state"))
                        .reg_date(rs.getDate("reg_date") != null ? rs.getDate("reg_date").toLocalDate() : null)
                        .leave_date(rs.getDate("leave_date") != null ? rs.getDate("leave_date").toLocalDate() : null)
                        .pwd_change_date(rs.getDate("pwd_change_date") != null ? rs.getDate("leave_date").toLocalDate() : null)
                        .build();

            }
            else {
                vo = null;
            }
        }
        else {
            log.info("MemberDAO - 회원로그인 실패");
            vo = null;
        }
        return vo;
    }
}
