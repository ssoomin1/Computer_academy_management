//회원에 대한 기본관리
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

public class Person {
	//static final String driver="com.mysql.jdbc.Driver";
	//static final String url="jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	//static final String user="root";
	//static final String pw="tlstnals123!";
	//private static Connection conn = null;

	public static void main(String[] args) throws ClassNotFoundException{
		working();
	}
	
	public static void working() throws ClassNotFoundException {
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
		String user="root";
		String pw="tlstnals123!";
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,pw);
			//연결 확인
			System.out.println("connect"); 
		    
			Scanner scan=new Scanner(System.in);
			System.out.println("=========================전화번호 형식 : 010-xxxx-xxxx,날짜 형식:yyyy-MM-dd===============================");
			System.out.println("                           1. 회원 추가     2.회원 찾기  3.회원 정보 수정하기    4.회원 삭제하기  5.프로그램 종료                              ");
			System.out.println("======================================================================================================");
			System.out.print(">>>> ");
			int ChooseNum=scan.nextInt();
			//회원 추가하기
			if(ChooseNum ==1) {
				System.out.println("=============1.회원 추가 ================");
				System.out.print("이름 : ");
                String name = scan.next();
                System.out.print("회원 전화번호 : ");
                String PhoneNum = scan.next();
                //등록일에 현재 날짜를 넣음
                SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
                Date time=new Date();
                String time1=format1.format(time);
                System.out.println("등록일: "+time1);
                System.out.print("생일: ");
                String birthday=scan.next();
                System.out.println("");
                //curriculum table을 불러와서 출력함 -> 관리자가 번호를 보고 뭔지 알 수 있게.
                System.out.println("참고사항- 수업");
                String csql="SELECT * FROM curriculum";
    			pst=conn.prepareStatement(csql);
    			rs=pst.executeQuery();
    			while(rs.next()) {
    				int id = rs.getInt("id");
    				String classroom = rs.getString("classroom");  
    				int fee = rs.getInt("fee");
    				String description=rs.getString("description");
    				System.out.println("id : "+id+", classroom : "+classroom+", fee: "+fee+", description: "+description);
    			}System.out.println(" ");
                System.out.print("수업 (1~8): ");
                int classroom=scan.nextInt();
                System.out.print("학부모 이름: ");
                String parentName = scan.next();        
                System.out.print("부모님 전화번호 : ");
                String parentPN = scan.next();
                System.out.print("학생과의 관계 (1-엄마  2-아빠  3-조부모) : ");
                int r=scan.nextInt();
                System.out.println("");
                String sql="INSERT INTO personal VALUES ((SELECT(MAX(id)+1)FROM personal p),?,?,?,?,?,?,?,?,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2,PhoneNum);
                pst.setString(3, time1);
                pst.setString(4, birthday);
                pst.setInt(5, classroom);
                pst.setString(6,null);
                pst.setString(7, null);
                //8과 9는 각각 출석클래스와,pay클래스에서 입력받음 -> null
                pst.setString(8,null);
                pst.setString(9,parentName);
                pst.setString(10, parentPN);
                pst.setInt(11,r);
               
                System.out.println("===========이름, 전화번호, 생년월일, 등록일, 수강반 , 학부모 성함, 학부모 전화번호, 관계 순서입니다.==========");
        		System.out.println(name+", "+PhoneNum+", "+parentPN+", "+birthday+", "+time1+", "+classroom+", "+parentName+", "+parentPN+", "+r);
        		System.out.print("\n");
        		System.out.print("맞습니까?(y/n)>> ");
        		String answer=scan.next();
        		System.out.println("");
        		switch(answer) {
        		case "y":pst.executeUpdate();
                System.out.println("회원정보가 입력되었습니다. ");
                System.out.println("");
                working(); break;
        		case "n": System.out.println("정보를 다시 입력해주세요."); working();break;
        		}
			}
			else if(ChooseNum==2) {
				System.out.println("1. 이름으로 검색");
				System.out.println("2. 학생 전화번호로 검색");
				System.out.print("숫자를 선택하세요.(1-2) >> ");
				int num = scan.nextInt();
				switch(num) {
				case 1: 
				{
					System.out.print("학생 이름을 입력해주세요 >> ");
					String name = scan.next();
					/*String sql="SELECT p.id, p.Name,p.PhoneNum,p.regdate,p.birthday,ci.classroom,ci.fee,ci.description,p.attendance,p.grade,p.parentName,p.parentPN,re.relation FROM personal AS p JOIN curriculum AS ci ON p.classroom=ci.id AND p.Name='"+name
							+"' JOIN relation AS re ON p.r=re.id AND p.Name='"+name+"';";*/
					String sql="SELECT p.id,p.Name,p.PhoneNum,p.regdate,p.birthday,ci.classroom,ci.fee,ci.description,p.attendance,p.parentName,p.parentPN,r.relation"
							+ "FROM personal as p JOIN curriculum as ci ON p.classroom=ci.id AND p.Name='"+name
							+ "'JOIN relation as r ON p.r=r.id AND p.Name= '"+name+ "';";
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();
				}break;
				case 2:
				{
					System.out.print("전화번호 입력해주세요 >> ");
					String PhoneNum=scan.next();
					
					String sql = "SELECT p.id,p.Name,p.PhoneNum,p.regdate,p.birthday,ci.classroom,ci.fee,ci.description,p.attendance,p.parentName,p.parentPN,r.relation"
							+"FROM pesonal as p JOIN curriculum as ci ON p.classroom=ci.id AND p.PhoneNum ='"+PhoneNum+"' JOIN relation as r ON p.r=r.id AND p.PhoneNum='"+"';";
					/*String sql = "SELECT p.id,p.Name,p.PhoneNum,p.regdate,p.birthday,ci.classroom,ci.fee,ci.description,p.attendance,p.parentName,p.parentPN,r.relation"
							+ " FROM personal AS p JOIN curriculum AS ci ON p.classroom=ci.id AND p.PhoneNum = '"
							+PhoneNum+"' JOIN relation AS r ON p.r=r.relation AND p.PhoneNum='"+PhoneNum+"';";*/
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();

				}break;
				}
				if (rs == null) System.out.println("해당 내용을 찾을 수 없습니다.");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String Name = rs.getString("name");  
					String PhoneNum = rs.getString("PhoneNum");
					String parentPN=rs.getString("parentPN");
					String regdate=rs.getString("regdate");
					String birthday=rs.getString("birthday");
					//curriculum table안에 있는 classroom을 가져와야함
					String classroom=rs.getString("ci.classroom");
					//conditions table 안에 있는 String형의 currentS를 가져와야 함
					String condition_now=rs.getString("c.currentS");
					//값을 출력해서 찾아줌
					System.out.println("회원번호: "+id+", 이름: "+Name+", 학생전화번호: "+PhoneNum+", 학부모전화번호 : "+parentPN+", 등록일: "+regdate
							+", 생년월일: "+birthday+", 수업: "+classroom+" 현재 상태:"+condition_now);
					System.out.println("");
				}
				working();
			}
			else if(ChooseNum==3) {
				//내용 수정
				System.out.println("회원의 회원번호와 이름, 전화번호를 입력해주세요.");
				int s_id=scan.nextInt();
				String s_name=scan.next();
				String s_PN=scan.next();
				String sql = "SELECT * FROM personal WHERE id=? AND Name=? AND PhoneNum=?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, s_id); //학생 회원번호
				pst.setString(2, s_name); //학생 이름
				pst.setString(3, s_PN); //학생 부모 전화번호
				rs = pst.executeQuery(); 
				
				while(rs.next()) {
					String id = rs.getString("id");
					String Name = rs.getString("Name");
					String PhoneNum= rs.getString("PhoneNum");
					System.out.println("회원 번호: "+id+", 이름: "+Name+", 전화번호: "+PhoneNum+" 님이 맞습니까?(y/n)");
				}
				String answer=scan.next();
				if(answer.equals("y")) {
					System.out.println("수정하고 싶은 항목이 무엇인가요?");
					System.out.println("1. 학생 정보 \t 2.학부모 정보");
					int i=scan.nextInt();
					switch (i){
					case 1: System.out.println("수정하고 싶은 항목이 무엇인가요?");
							System.out.println("1.학생 전화번호  \t 2.수강과목 \t");
							System.out.print(">>>");
							int ChooseN=scan.nextInt();
							switch(ChooseN) {
							case 1:
								System.out.print("수정할 전화번호를 입력하세요: ");
								String SPhoneNum=scan.next(); //학생 전화번호 입력 받음
								String sql2="UPDATE personal SET PhoneNum='"+SPhoneNum+"' WHERE id="+s_id+";";
								pst=conn.prepareStatement(sql2);
								
								System.out.println(SPhoneNum+" 이(가) 맞나요?? (y/n) >>");
								String Answer=scan.next();
								if(Answer.equals("y")){
									System.out.println("수정이 완료되었습니다.");
									pst.executeUpdate();
									conn.close();
								}else {
									System.out.println("다시 입력해주세요.");working();
								}
								break;
							case 2:
								System.out.print("수정할 과목을 입력하세요: ");
								String SPhoneNum1=scan.next();
								String csql="UPDATE personal SET PhoneNum='"+SPhoneNum1+"' WHERE id="+s_id+";";
								pst=conn.prepareStatement(csql);
				    			rs=pst.executeQuery();
				    			while(rs.next()) {
				    				int id = rs.getInt("id");
				    				String classroom = rs.getString("classroom");  
				    				int fee = rs.getInt("fee");
				    				String description=rs.getString("description");
				    				System.out.println("id : "+id+", classroom : "+classroom+", fee: "+fee+", description: "+description);
				    			}System.out.println(" ");
								pst=conn.prepareStatement(csql);
				    			rs=pst.executeQuery();
				    			while(rs.next()) {
				    				int id = rs.getInt("id");
				    				String classroom = rs.getString("classroom");  
				    				int fee = rs.getInt("fee");
				    				String description=rs.getString("description");
				    				System.out.println("id : "+id+", classroom : "+classroom+", fee: "+fee+", description: "+description);
				    			}System.out.println(" ");
								int classroom=scan.nextInt(); //과목 입력받기
								String sql4="UPDATE personal SET classroom='"+classroom+"' WHERE id="+s_id+";";
								pst=conn.prepareStatement(sql4);
								
								System.out.println(classroom+" 이(가) 맞나요?? (y/n) >>");
								String ANSwer=scan.next();
								if(ANSwer.equals("y")){
									System.out.println("수정이 완료되었습니다.");
									pst.executeUpdate();
									conn.close();
								}else {
									System.out.println("다시 입력해주세요.");working();
								}
								break;
							case 3: 
								
								System.out.print("수정할 학생의 현재 상태를 입력하세요: ");
								int s_condition=scan.nextInt();
								//String sql5="UPEATE personal SET condition_now="+s_condition+" WHERE id="+s_id+";";
								String sql5="UPDATE personal SET condition_now = ? WHERE id = ?";
								pst=conn.prepareStatement(sql5);
								pst.setInt(1, s_condition);
								pst.setInt(2, s_id);
								
								System.out.println(s_condition+" 이(가) 맞나요?? (y/n) >>");
								String ANSWER=scan.next();
								if(ANSWER.equals("y")){
									pst.executeUpdate();
									conn.close();
									System.out.println("수정이 완료되었습니다.");
								}else {
									System.out.println("다시 입력해주세요.");working();
								}
								break;
							}
					}
					
				
					
					working();
				}
			}
			else if(ChooseNum==4) {
				System.out.print("학생 전화번호를 입력해주세요. >> ");
				String pn=scan.next();
				System.out.print("전화번호: "+pn+"\t 정말로 삭제하시겠습니까?? (y/n) >> ");
				String a=scan.next();
				if(a.equals("y")) {
					String sql6="DELETE FROM personal WHERE PhoneNum = ?";
					pst=conn.prepareStatement(sql6);
					pst.setString(1,pn);
					pst.execute();
					conn.close();
					System.out.println("삭제되었습니다.");
				}else{
					System.out.println("취소합니다.");
				}
			}
			else if(ChooseNum==5) {
				System.out.println("학생 관리 프로그램을 종료합니다. ");
			}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("SQL Error: "+e.getMessage());
			}
		    finally{
		    	if(pst!=null)try {pst.close();}catch(SQLException sqle) {}
		    	if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
			}
	   }
  }

