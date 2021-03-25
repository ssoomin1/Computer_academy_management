//ȸ���� ���� �⺻����
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
			//���� Ȯ��
			System.out.println("connect"); 
		    
			Scanner scan=new Scanner(System.in);
			System.out.println("=========================��ȭ��ȣ ���� : 010-xxxx-xxxx,��¥ ����:yyyy-MM-dd===============================");
			System.out.println("                           1. ȸ�� �߰�     2.ȸ�� ã��  3.ȸ�� ���� �����ϱ�    4.ȸ�� �����ϱ�  5.���α׷� ����                              ");
			System.out.println("======================================================================================================");
			System.out.print(">>>> ");
			int ChooseNum=scan.nextInt();
			//ȸ�� �߰��ϱ�
			if(ChooseNum ==1) {
				System.out.println("=============1.ȸ�� �߰� ================");
				System.out.print("�̸� : ");
                String name = scan.next();
                System.out.print("ȸ�� ��ȭ��ȣ : ");
                String PhoneNum = scan.next();
                //����Ͽ� ���� ��¥�� ����
                SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
                Date time=new Date();
                String time1=format1.format(time);
                System.out.println("�����: "+time1);
                System.out.print("����: ");
                String birthday=scan.next();
                System.out.println("");
                //curriculum table�� �ҷ��ͼ� ����� -> �����ڰ� ��ȣ�� ���� ���� �� �� �ְ�.
                System.out.println("�������- ����");
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
                System.out.print("���� (1~8): ");
                int classroom=scan.nextInt();
                System.out.print("�кθ� �̸�: ");
                String parentName = scan.next();        
                System.out.print("�θ�� ��ȭ��ȣ : ");
                String parentPN = scan.next();
                System.out.print("�л����� ���� (1-����  2-�ƺ�  3-���θ�) : ");
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
                //8�� 9�� ���� �⼮Ŭ������,payŬ�������� �Է¹��� -> null
                pst.setString(8,null);
                pst.setString(9,parentName);
                pst.setString(10, parentPN);
                pst.setInt(11,r);
               
                System.out.println("===========�̸�, ��ȭ��ȣ, �������, �����, ������ , �кθ� ����, �кθ� ��ȭ��ȣ, ���� �����Դϴ�.==========");
        		System.out.println(name+", "+PhoneNum+", "+parentPN+", "+birthday+", "+time1+", "+classroom+", "+parentName+", "+parentPN+", "+r);
        		System.out.print("\n");
        		System.out.print("�½��ϱ�?(y/n)>> ");
        		String answer=scan.next();
        		System.out.println("");
        		switch(answer) {
        		case "y":pst.executeUpdate();
                System.out.println("ȸ�������� �ԷµǾ����ϴ�. ");
                System.out.println("");
                working(); break;
        		case "n": System.out.println("������ �ٽ� �Է����ּ���."); working();break;
        		}
			}
			else if(ChooseNum==2) {
				System.out.println("1. �̸����� �˻�");
				System.out.println("2. �л� ��ȭ��ȣ�� �˻�");
				System.out.print("���ڸ� �����ϼ���.(1-2) >> ");
				int num = scan.nextInt();
				switch(num) {
				case 1: 
				{
					System.out.print("�л� �̸��� �Է����ּ��� >> ");
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
					System.out.print("��ȭ��ȣ �Է����ּ��� >> ");
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
				if (rs == null) System.out.println("�ش� ������ ã�� �� �����ϴ�.");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String Name = rs.getString("name");  
					String PhoneNum = rs.getString("PhoneNum");
					String parentPN=rs.getString("parentPN");
					String regdate=rs.getString("regdate");
					String birthday=rs.getString("birthday");
					//curriculum table�ȿ� �ִ� classroom�� �����;���
					String classroom=rs.getString("ci.classroom");
					//conditions table �ȿ� �ִ� String���� currentS�� �����;� ��
					String condition_now=rs.getString("c.currentS");
					//���� ����ؼ� ã����
					System.out.println("ȸ����ȣ: "+id+", �̸�: "+Name+", �л���ȭ��ȣ: "+PhoneNum+", �кθ���ȭ��ȣ : "+parentPN+", �����: "+regdate
							+", �������: "+birthday+", ����: "+classroom+" ���� ����:"+condition_now);
					System.out.println("");
				}
				working();
			}
			else if(ChooseNum==3) {
				//���� ����
				System.out.println("ȸ���� ȸ����ȣ�� �̸�, ��ȭ��ȣ�� �Է����ּ���.");
				int s_id=scan.nextInt();
				String s_name=scan.next();
				String s_PN=scan.next();
				String sql = "SELECT * FROM personal WHERE id=? AND Name=? AND PhoneNum=?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, s_id); //�л� ȸ����ȣ
				pst.setString(2, s_name); //�л� �̸�
				pst.setString(3, s_PN); //�л� �θ� ��ȭ��ȣ
				rs = pst.executeQuery(); 
				
				while(rs.next()) {
					String id = rs.getString("id");
					String Name = rs.getString("Name");
					String PhoneNum= rs.getString("PhoneNum");
					System.out.println("ȸ�� ��ȣ: "+id+", �̸�: "+Name+", ��ȭ��ȣ: "+PhoneNum+" ���� �½��ϱ�?(y/n)");
				}
				String answer=scan.next();
				if(answer.equals("y")) {
					System.out.println("�����ϰ� ���� �׸��� �����ΰ���?");
					System.out.println("1. �л� ���� \t 2.�кθ� ����");
					int i=scan.nextInt();
					switch (i){
					case 1: System.out.println("�����ϰ� ���� �׸��� �����ΰ���?");
							System.out.println("1.�л� ��ȭ��ȣ  \t 2.�������� \t");
							System.out.print(">>>");
							int ChooseN=scan.nextInt();
							switch(ChooseN) {
							case 1:
								System.out.print("������ ��ȭ��ȣ�� �Է��ϼ���: ");
								String SPhoneNum=scan.next(); //�л� ��ȭ��ȣ �Է� ����
								String sql2="UPDATE personal SET PhoneNum='"+SPhoneNum+"' WHERE id="+s_id+";";
								pst=conn.prepareStatement(sql2);
								
								System.out.println(SPhoneNum+" ��(��) �³���?? (y/n) >>");
								String Answer=scan.next();
								if(Answer.equals("y")){
									System.out.println("������ �Ϸ�Ǿ����ϴ�.");
									pst.executeUpdate();
									conn.close();
								}else {
									System.out.println("�ٽ� �Է����ּ���.");working();
								}
								break;
							case 2:
								System.out.print("������ ������ �Է��ϼ���: ");
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
								int classroom=scan.nextInt(); //���� �Է¹ޱ�
								String sql4="UPDATE personal SET classroom='"+classroom+"' WHERE id="+s_id+";";
								pst=conn.prepareStatement(sql4);
								
								System.out.println(classroom+" ��(��) �³���?? (y/n) >>");
								String ANSwer=scan.next();
								if(ANSwer.equals("y")){
									System.out.println("������ �Ϸ�Ǿ����ϴ�.");
									pst.executeUpdate();
									conn.close();
								}else {
									System.out.println("�ٽ� �Է����ּ���.");working();
								}
								break;
							case 3: 
								
								System.out.print("������ �л��� ���� ���¸� �Է��ϼ���: ");
								int s_condition=scan.nextInt();
								//String sql5="UPEATE personal SET condition_now="+s_condition+" WHERE id="+s_id+";";
								String sql5="UPDATE personal SET condition_now = ? WHERE id = ?";
								pst=conn.prepareStatement(sql5);
								pst.setInt(1, s_condition);
								pst.setInt(2, s_id);
								
								System.out.println(s_condition+" ��(��) �³���?? (y/n) >>");
								String ANSWER=scan.next();
								if(ANSWER.equals("y")){
									pst.executeUpdate();
									conn.close();
									System.out.println("������ �Ϸ�Ǿ����ϴ�.");
								}else {
									System.out.println("�ٽ� �Է����ּ���.");working();
								}
								break;
							}
					}
					
				
					
					working();
				}
			}
			else if(ChooseNum==4) {
				System.out.print("�л� ��ȭ��ȣ�� �Է����ּ���. >> ");
				String pn=scan.next();
				System.out.print("��ȭ��ȣ: "+pn+"\t ������ �����Ͻðڽ��ϱ�?? (y/n) >> ");
				String a=scan.next();
				if(a.equals("y")) {
					String sql6="DELETE FROM personal WHERE PhoneNum = ?";
					pst=conn.prepareStatement(sql6);
					pst.setString(1,pn);
					pst.execute();
					conn.close();
					System.out.println("�����Ǿ����ϴ�.");
				}else{
					System.out.println("����մϴ�.");
				}
			}
			else if(ChooseNum==5) {
				System.out.println("�л� ���� ���α׷��� �����մϴ�. ");
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

