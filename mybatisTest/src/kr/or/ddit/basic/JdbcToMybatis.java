package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
LPROD테이블에 새로운 데이터 추가하기

Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.

입력 받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.

 --> JDBC예제 중 JdbcTest05.java를 myBatis용으로 변경하시오.
 --> mapper파일명은 'jdbc-mapper.xml'로 한다.
*/

public class JdbcToMybatis {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Reader rd = null;
        SqlSessionFactory sqlSessionFactory = null;
        try {
            rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
        } catch (Exception e) {
            System.out.println("myBatis 초기화 실패");
            e.printStackTrace();
        } finally {
            if(rd != null) try {rd.close();} catch (IOException e) {}
        }
        
        SqlSession session = null;
        
        System.out.println("inset 시작..");
        System.out.print("lprod_gu 입력 >> ");
        String lgu = scan.next();
        try {
            session = sqlSessionFactory.openSession();
            LprodVO lvo = session.selectOne("jdbc.getLpord", lgu);
            
            if(lvo != null) {
                System.out.println("중복된 데이터가 있습니다.류빵구");
            } else {
                System.out.print("lprod_nm 입력 >> ");
                String lnm = scan.next();
                LprodVO vo = new LprodVO();
                vo.setLprod_gu(lgu);
                vo.setLprod_nm(lnm);
                int insertCnt = session.insert("jdbc.insertLprod", vo);
                if(insertCnt>0) {
                    System.out.println("insert 성공");
                } else {
                    System.out.println("insert 실패");
                }
            }
        } finally {
            session.commit();
            session.close();
        }
    }

}
