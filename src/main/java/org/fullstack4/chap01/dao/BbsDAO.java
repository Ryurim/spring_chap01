package org.fullstack4.chap01.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.domain.BbsVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BbsDAO {
    public String getTime() {
        String now = null;

        try(    //try with resource 구문의 특징 : ()에 조건을 넣어주면 이 안에서 오픈된 리소스는 자동으로 close 됨! close 안해도 된다
                //하지만 필드에서는 여기에 들어갈게 많기 때문에 다 여기에 넣을 수 없어. {} 안에 쓰고 close 해줘야함
                Connection conn = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("select now()");
                ResultSet rs = preparedStatement.executeQuery();
                ) {
                    rs.next();
                    now = rs.getString(1);
        } catch(Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {
        //롬복의 Cleanup 사용. 롬복이 이 프로세스가 끝나는 시점에(return now) 이놈들의 객체를 모두 자동으로 close 시킴
        //close() 처리 코드를 자동으로 컴파일 시점에 완성. 별도의 try~ 등의 코드가 필요하지 않아서 간결한 코드 완성
        //Lombok 라이브러리를 반드시 사용해야함. 의존성을 줄일 수 있따,,
        //코딩량도 줄고 프로세스 로직 자체도 깔끔해짐! 이해하기도 쉬워서 사용하는 것임
        //롬복 사용하면 try-catch 구문이 없어서 함수 자체에서 throws로 던짐 -> 상위 클래스 -> 그것의 parent -> 다 처리 안하면 JVM이 처리함
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = conn.prepareStatement("select now()");
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();
        String now = rs.getString(1);
        return now;
    }

    public int regist(BbsVO vo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tbl_bbs ");
        sb.append(" (user_id, title, content, display_date, reg_date, read_cnt) ");
        sb.append(" VALUES(?, ?, ?, ?, now(), ?)");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1, vo.getUser_id());
        pstmt.setString(2, vo.getTitle());
        pstmt.setString(3, vo.getContent());
        pstmt.setString(4, vo.getDisplay_date());
        pstmt.setInt(5, 0);

//        int result = pstmt.executeUpdate();
        return pstmt.executeUpdate();
    }

    public List<BbsVO> list() throws Exception{
        StringBuilder sb = new StringBuilder();
        // 트래픽 때문에 웬만해서 * 쓰지 말자!
        sb.append("SELECT idx, user_id, title, content, display_date, read_cnt, reg_date, modify_date ");
        sb.append(" FROM tbl_bbs ");
        sb.append(" ORDER BY idx DESC");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<BbsVO> bbsList = new ArrayList<>();
        while(rs.next()) {
            BbsVO vo = BbsVO.builder()
                    .idx(rs.getInt("idx"))
                    .user_id(rs.getString("user_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .display_date(rs.getString("display_date"))
                    .read_cnt(rs.getInt("read_cnt"))
                    .reg_date(rs.getDate("reg_date").toLocalDate())
                    .modify_date(rs.getDate("modify_date") != null ? rs.getDate("modify_date").toLocalDate() : null)
                    .build();
            bbsList.add(vo);
        }

        return bbsList;
    }

    public BbsVO view(int idx) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT idx, user_id, title, content, display_date, read_cnt ");
        sb.append(" , reg_date ");
        sb.append(" FROM tbl_bbs ");
        sb.append(" WHERE idx = ?");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setInt(1, idx);   // 첫번째 파라미터에 idx 값을 넣을게요
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        BbsVO vo = BbsVO.builder()
                .idx(rs.getInt("idx"))
                .user_id(rs.getString("user_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .display_date(rs.getString("display_date"))
                .read_cnt(rs.getInt("read_cnt"))
                .reg_date(rs.getDate("reg_date").toLocalDate())
                .build();

        return vo;
    }

    public int modify(BbsVO vo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tbl_bbs SET ");
        sb.append(" user_id = ? ");
        sb.append(" , title = ? ");
        sb.append(" , content = ? ");
        sb.append(" , display_date = ? ");
        //sb.append(" , read_cnt = ? ");
        sb.append(" , modify_date = now() ");
        sb.append(" WHERE idx = ? ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1, vo.getUser_id());
        pstmt.setString(2, vo.getTitle());
        pstmt.setString(3, vo.getContent());
        pstmt.setString(4, vo.getDisplay_date());
        //pstmt.setInt(5, vo.getRead_cnt());
        pstmt.setInt(5, vo.getIdx());

        int result = pstmt.executeUpdate();
        System.out.println("======================================");   //배포할 때 dao랑 서비스에 최대한 로그 안쓰는게 좋아. 개발단계에서 그냥 보려고 쓰는거!
        System.out.println("BbsDAO >> modify(BbsVO vo) : " + result);
        System.out.println("======================================");

        return result;
    }

    public int delete(int idx) throws Exception {
        String sql = "DELETE FROM tbl_bbs WHERE idx = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, idx);

        int result = pstmt.executeUpdate();

        log.info("======================================");
        log.info("BbsDAO >> delete(int idx) : " + result);
        log.info("======================================");

        return result;
    }
}
