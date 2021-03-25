//학부모에 대한 기본관리
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

public class Parent{
	static final String driver="com.mysql.jdbc.Driver";
	static final String url="jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	static final String user="root";
	static final String pw="111111";
	private static Connection conn;

	public static void main(String[] args) throws ClassNotFoundException{
		Parentworking();
	}
	
	public static void Parentworking() throws ClassNotFoundException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,pw);
			//연결 확인
			//System.out.println("connect"); 
		    
			Scanner scan=new Scanner(System.in);
			System.out.println("================전화번호 형식 : 010-xxxx-xxxx,날짜 형식:yyyy-MM-dd====================");
			System.out.println("                                 1. 학부모 추가    2.프로그램 종료하기                                           ");
			System.out.println("==============================================================================");
			int ChooseNum=scan.nextInt();
			//학부모 추가하기
			if(ChooseNum ==1) {
				
				System.out.print("이름 : ");
                String parent_name = scan.next();
                System.out.print("전화번호 : ");
                String parents_Pnum = scan.next();
                System.out.print("생년월일: ");
                String birthday=scan.next();
                System.out.print("학생과의 관계 : ");
                String relation = scan.next();
                //insert information
                String sql="INSERT INTO parent_db VALUES ((SELECT (MAX(parent_id) + 1) FROM parent_db AS pdb),?,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setString(1, parent_name);
                pst.setString(2,parents_Pnum);
                pst.setString(3, birthday);
                pst.setString(4, relation);
                
                
                
                System.out.println("==이름, 전화번호, 생년월일, 학생과의 관계입니다.==");
        		System.out.println(parent_name+", "+parents_Pnum+", "+birthday+", "+relation+", ");
        		System.out.print("\n");
        		System.out.print("맞습니까?(y/n)>> ");
        		String answer=scan.next();
        		switch(answer) {
        		//만약 중복되는 전화번호가 있다면 중복되어있다고 뜨고 없으면 추가
        		case "y":pst.executeUpdate();
                System.out.println("회원정보가 입력되었습니다. ");
                System.out.println("");
                Parentworking(); break;
                //출력된 상태가 틀렸다면 
        		case "n": System.out.println("정보를 다시 입력해주세요."); Parentworking();break;
        		}		
			}
			else if(ChooseNum==2) {
				System.out.println("학부모 관리 프로그램을 종료합니다.");
			}
			}catch(SQLException e) {
				//e.printStackTrace();
				//System.out.println("SQL Error: "+e.getMessage());
				System.out.println("!!!이미 있는 정보입니다!!!");Parentworking();
			}
		    finally{
		    	//if(rs != null) {rs.close();}
		    	if(pst!=null)try {pst.close();}catch(SQLException sqle) {}
		    	if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
			}
	   }
  }