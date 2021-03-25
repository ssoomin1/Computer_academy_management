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
			System.out.println("���� ����");
			
			System.out.println("============== GRADE ==============");
        	while(true) {
            	System.out.print("������ �Է��ϰ� ���� �л��� ��ȭ��ȣ�� �Է��ϼ��� >> ");
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
					System.out.println("�������- ����");
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
					System.out.print("�̸� : "+user+" ������ : "+classroom+" ��ȭ��ȣ : "+pnum+"���� �½��ϱ�? ");
				}//�Է¹��� �������� ȸ�� ���� ������ �� ��ġ���̶� �Ȱ���
            	String answer = scan.next();
            	if(answer.equals("y")) { //ȸ���� ������
            		System.out.print("����� Ȯ���ϰڽ��ϱ�? >> (y/n) ");
            		String answer2 = scan.next();
            		if(answer2.equals("y")) { //��� Ȯ���ϰڴٰ� �ϸ�
            			con=DriverManager.getConnection(URL,USER,PASSWD);
            			String sql1 = "update personal set grade = ? where PhoneNum = ?"; //��ȭ��ȣ�� �ִ� �⼮�ٲٰڴ�
            			pstmt = con.prepareStatement(sql1);
            			
            			System.out.print("������ �Է����ּ��� >> ");
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
            			pstmt.setString(1, grade); //2 ��¥�� �ٲٰڴ�.
            			
            			
            			pstmt.setString(2, pnum); //1 �Է��� ��ȭ��ȣ�� �ִ� 
            			
            			pstmt.executeUpdate();
            			
            			con.close();
            			
            			System.out.println(user+"���� ������ "+score+"���̰� "+grade+"����Դϴ�.");
            			System.out.println("���� �Է°� ��� ������ �Ϸ��Ͽ����ϴ�.");break;
            		}else {
            			System.out.println("��� ������ ���� �ʰڽ��ϴ�."); break;
            		}
            	}else {
            		System.out.println("�ٽ� �Է��ϼ���.");
            		System.out.println("");
            	}
        	}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL Error : [" + e.getMessage()+"]");
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {} 
			//close ������ �߿��ϴ�.
			if(con!=null)try {con.close();}catch(SQLException sqle) {}
			
		}
	}

}


