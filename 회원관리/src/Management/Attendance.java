//출석 클래스
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Attendance {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	public static final String USER ="root";
	public static final String PASSWD ="111111";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");	//현재 날자 출력시 형태  - yyyy-MM-dd
        
        String format_time = format.format (System.currentTimeMillis());
        
        try {
        	con=DriverManager.getConnection(URL,USER,PASSWD);
        	
        	System.out.println("==============출석 확인==============");
        	while(true) {
            	System.out.println("출석하고 싶은 회원의 전화번호를 입력하세요.");
            	String pnum = scan.next();   //회원의 전화번호 입력 받기
            	
            	String sql = "select * from personal where PhoneNum = ?";  //personal 테이블에, 원하는 전화번호의 열 가져오기
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pnum);	//입력받은 전화번호로 원하는 열 찾기
				rs = pstmt.executeQuery(); 
				
				while(rs.next()) {
					String id = rs.getString("id");		//출력할 회원의 id 찾기
					String Name = rs.getString("Name");	//출력할 회원의 이름 찾기
					String PhoneNum= rs.getString("PhoneNum");	//출력할 회원의 전화번호 찾기
					
					System.out.print("회원 번호: "+id+", 이름: "+Name+", 전화번호: "+PhoneNum+" 님이 맞습니까?(y/n) >> ");
				}//입력받은 전화번호로 회원 정보 출력 후 본인이 맞는 지 확인하기
            	String answer = scan.next();
            	if(answer.equals("y")) { //회원이 맞으면(answer = y) 수행
            		System.out.print("출석하겠습니까?(y/n) >> ");
            		String answer2 = scan.next();           		
            		if(answer2.equals("y")) { //출석하겠다고 하면(answer2 = y) 수행
            			con=DriverManager.getConnection(URL,USER,PASSWD);
            			String sql1 = "update personal set attendance = ? where PhoneNum = ?"; //입력한 전화번호와 같은 열의 출석 업데이트하기
            			pstmt = con.prepareStatement(sql1);
            			pstmt.setString(1, format_time); 	//2 날짜를 현재 날짜로 업데이트 하기
            			pstmt.setString(2, pnum); 			//1 입력한 전화번호와 같은 열의
            			
            			pstmt.executeUpdate();
            			
            			con.close();
            			System.out.println(format_time+" 오늘 출석을 완료하였습니다.");System.out.println("=================================");break;
            		}else {
            			System.out.println("출석 체크를 하지 않겠습니다.");System.out.println("================================="); break;
            		}
            	}else {
            		System.out.println("다시 입력하세요.");
            		System.out.println("=================================");
            	}
        	}
        	
        	
        }catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL Error : [" + e.getMessage()+"]");
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {} 
			//close 순서가 중요하다.
			if(con!=null)try {con.close();}catch(SQLException sqle) {}
			
		}
	}

}