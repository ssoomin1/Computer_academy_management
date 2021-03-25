package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GradeManagement {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	public static final String USER ="root";
	public static final String PASSWD ="111111";
	Connection con=null;
	PreparedStatement pstmt=null;
    ResultSet rs = null;
    
    public GradeManagement() {
    	Grade();
    }
	public void Grade() {
		Scanner scan = new Scanner(System.in);
		try {
			con=DriverManager.getConnection(URL,USER,PASSWD);
			System.out.println("연결 성공");
			
			System.out.println("============== GRADE ==============");
        	while(true) {
            	System.out.print("점수를 입력하고 싶은 학생의 전화번호를 입력하세요 >> ");
            	String pnum = scan.next();   
            	
            	String sql = "select name, classroom from personal where PhoneNum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pnum);
				rs = pstmt.executeQuery(); 
				String user = null;
				String classroom = null;
				while(rs.next()) {
					user = rs.getString("name");
					classroom = rs.getString("classroom");
					
					System.out.println("");
					System.out.println("참고사항- 수업");
	                String csql="SELECT * FROM curriculum";
	    			pstmt=con.prepareStatement(csql);
	    			rs=pstmt.executeQuery();
	    			while(rs.next()) {
	    				int id = rs.getInt("id");
	    				String classroom1 = rs.getString("classroom");  
	    				int fee = rs.getInt("fee");
	    				String description=rs.getString("description");
	    				System.out.println("id : "+id+", classroom : "+classroom1+", fee: "+fee+", description: "+description);
	    			}System.out.println(" ");
					System.out.print("이름 : "+user+" 수강반 : "+classroom+" 전화번호 : "+pnum+"님이 맞습니까? ");
				}//입력받은 전번으로 회원 정보 나오게 함 서치문이랑 똑같음
            	String answer = scan.next();
            	if(answer.equals("y")) { //회원이 맞으면
            		System.out.print("등급을 확인하겠습니까? >> (y/n) ");
            		String answer2 = scan.next();
            		if(answer2.equals("y")) { //등급 확인하겠다고 하면
            			con=DriverManager.getConnection(URL,USER,PASSWD);
            			String sql1 = "update personal set grade = ? where PhoneNum = ?"; //전화번호에 있는 출석바꾸겠다
            			pstmt = con.prepareStatement(sql1);
            			
            			System.out.print("성적을 입력해주세요 >> ");
            			int score = scan.nextInt();
            			String grade;
            			if(score== 100) {
            				grade = "S";
            			}
            			else if(score>=90 && score < 100) {
            				grade = "A";
            			}
            			else if(score>=80 && score < 90) {
            				grade = "B";
            			}
            			else if(score>=70 && score < 80){
            				grade = "C";
            			}
            			else {
            				grade = "D";
            			}
            			pstmt.setString(1, grade); //2 날짜를 바꾸겠다.
            			
            			
            			pstmt.setString(2, pnum); //1 입력한 전화번호에 있는 
            			
            			pstmt.executeUpdate();
            			
            			con.close();
            			
            			System.out.println(user+"님의 점수는 "+score+"점이고 "+grade+"등급입니다.");
            			System.out.println("성적 입력과 등급 산출을 완료하였습니다.");break;
            		}else {
            			System.out.println("등급 산출을 하지 않겠습니다."); break;
            		}
            	}else {
            		System.out.println("다시 입력하세요.");
            		System.out.println("");
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


