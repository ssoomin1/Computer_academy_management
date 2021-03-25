//결제클래스
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Pay {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	public static final String USER ="root";
	public static final String PASSWD ="tlstnals123!";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PAY();
	}
	public static void PAY() {
		Scanner scan = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	con=DriverManager.getConnection(URL,USER,PASSWD);
        	
        	String sql = "select p.id, p.Name, p.PhoneNum, c.classroom,c.fee,c.description FROM personal AS p JOIN curriculum AS c ON p.classroom = c.id AND p.PhoneNum=?";
        	//curriculum의 id와 personal의 classroom이 일치할 때, 
        	//personal 테이블과 curriculum 테이블을 JOIN하여 
        	//personal 테이블의 id, name, PhoneNum과
        	//curriculum 테이블의 classroom, fee, description 선택
        	
        	System.out.println("============== 수강비 결제 ==============");
        	System.out.print("회원(학생)님의 전화번호를 입력하세요.>>>");
        	String PhoneNumber=scan.next();
        	
        	pstmt = con.prepareStatement(sql);
			pstmt.setString(1, PhoneNumber); //입력받은 전화번호로 원하는 열 찾기
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				int id = rs.getInt("p.id");		//출력할 회원의 id 찾기
				String Name = rs.getString("p.Name");	//출력할 회원의 이름 찾기
				String PhoneNum = rs.getString("p.PhoneNum"); //출력할 회원의 전화번호 찾기

				System.out.printf("ID : %s 이름 : %s 전화번호 : %s 님이 맞습니까?(y/n) >> ", id, Name, PhoneNum);
			}//입력받은 전화번호로 회원 정보 출력 후 본인이 맞는 지 확인하기
			String answer = scan.next();
			System.out.println("");
			switch(answer) {
			case "y":	//결제 하겠다고 하면(answer = y) 수행

				rs = pstmt.executeQuery();
				while(rs.next()) {		//회원이 수강하는 반 정보출력
					String classroom = rs.getString("c.classroom");
					String fee = rs.getString("c.fee");
					String description = rs.getString("c.description");

					System.out.println("수강반  : " + classroom);
					System.out.println("수강비  : " + fee);
					System.out.println("반 설명 : " + description);
				}
				System.out.println("");
				System.out.print("학원비를 결제하겠습니까 ? (y/n) >> ");	
				String answer2 = scan.next();
				System.out.println("");
				switch(answer2) {
				case "y":	//결제 하겠다고 하면(answer2 = y) 수행
					String sql2 = "UPDATE personal P INNER JOIN curriculum C ON P.classroom = C.id SET P.Check_pay = ? WHERE p.PhoneNum = ? ";
					//
					pstmt = con.prepareStatement(sql2);
					SimpleDateFormat format1=new SimpleDateFormat("MM월");
					Date time=new Date();
					String time1=format1.format(time);
					String check_pay = time1+" 결제 완료";
        			pstmt.setString(1, check_pay);
        			pstmt.setString(2, PhoneNumber); 
        			System.out.println("결제를 완료하였습니다.");
        			
        			pstmt.executeUpdate();
        			
        			con.close();
        			
				case "n" :
					System.out.println("결제 시스템을 종료하겠습니다."); System.out.println("===================================="); break;
				default : System.out.println("잘못 입력하셨습니다.");
				};break;
			case "n":
				System.out.println("다시 입력하십시오.");break;
			default : System.out.println("잘못 입력하셨습니다."); break;
			};
        	
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