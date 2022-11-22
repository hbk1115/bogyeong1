package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;


public class Join extends JFrame{
   
	JLabel joinLabel = new JLabel("È¸¿ø°¡ÀÔ È­¸é");
	
    JLabel id;                   
    JTextField idField;                  //id ÀÔ·ÂÄ­
    JButton idcheck;                     //id Áßº¹È®ÀÎ ¹öÆ°
    boolean idcheckcheck = true;		 //id Áßº¹È®ÀÎ Çß´ÂÁö È®ÀÎ
 
    JLabel passCheck;				
    JPasswordField passField;            //password ÀÔ·ÂÄ­

    JLabel password;
    JPasswordField passCheckField;		 //password µ¿ÀÏ¿©ºÎ  

    JButton create;                      //»ý¼º ¹öÆ° Å¬¸¯½Ã µ¥ÀÌÅÍº£ÀÌ½º¿¡ ÀúÀå   
    JButton back;                        //back ¹öÆ° Å¬¸¯½Ã ¸ÞÀÎÈ­¸é(Main)À¸·Î °¨ 
    boolean overlapCheck = false;        //¾ÆÀÌµð Áßº¹µÇ¾ú´ÂÁö Ã¼Å©
  
    String user_ID;                      //È¸¿ø°¡ÀÔ ID
    String user_PASSWORD;                //È¸¿ø°¡ÀÔ ºñ¹Ð¹øÈ£
    String user_PASSRE;					 //ºñ¹Ð¹øÈ£ È®ÀÎ
    ResultSet result;
    public Join() {
        
    	this.setTitle("Join");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600, 530);
        setLocationRelativeTo(null);
        
        DataBase db = new DataBase();
        
        this.id = new JLabel("¾ÆÀÌµð");
        this.idField = new JTextField(20);
        this.idcheck = new JButton("Áßº¹ È®ÀÎ");
        
        this.joinLabel.setFont(new Font("Gothic", Font.BOLD, 20));
        this.joinLabel.setBounds(235, 50, 300, 50);
        this.id.setBounds(80, 200, 70, 40);
        this.idField.setBounds(200, 200, 200, 40);
        this.idcheck.setBounds(420, 200, 100, 40);
        //È­¸é À§ÀÇ Á¦¸ñ
        add(this.joinLabel);
        //¾ÆÀÌµð °ü·Ã
        add(this.id);           
        add(this.idField);
        add(this.idcheck);
     
        this.password = new JLabel("ºñ¹Ð¹øÈ£");
        this.passField = new JPasswordField(20);
        this.passCheck = new JLabel("ºñ¹Ð¹øÈ£ È®ÀÎ");
        this.passCheckField = new JPasswordField(20);
        
        this.password.setBounds(80, 270, 70, 40);
        this.passField.setBounds(200, 270, 200, 40);
        this.passCheck.setBounds(80, 340, 100, 40);
        this.passCheckField.setBounds(200, 340, 200, 40);
        //ºñ¹Ð¹øÈ£ °ü·Ã
        add(this.password);
        add(this.passField);
        add(this.passCheck);
        add(this.passCheckField);
        
        this.create = new JButton("»ý¼º");
        this.back = new JButton("µÚ·Î");
        
        this.create.setBounds(230, 410, 70, 40);
        this.back.setBounds(310, 410, 70, 40);
        //ºÎ°¡ ±â´É
        add(this.create);        
        add(this.back);
        
        //id Áßº¹Ã¼Å© ¹öÆ°(idcheck) ÀÌº¥Æ® 
        this.idcheck.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              String s = idField.getText();
              String pattern = "^[0-9|a-z|A-Z|¤¡-¤¾|¤¿-¤Ó|°¡-ÆR]*$"; // °ø¹é È¤Àº Æ¯¼ö¹®ÀÚ°¡ ÀÔ·ÂµÈ °æ¿ì(ÀÚ¹Ù Á¤±Ô½Ä Âü°í)
              try {
                result = db.getResult("select * from user"); //´Ù¸¥ Å×ÀÌºí ¾µ °æ¿ì user ¹Ù²Ù¸é µÊ
                
                while(result.next()) {
                     String user_ID = result.getString("username");
                     if(s.equals(user_ID)) {
                         overlapCheck = true; //Áßº¹µÈ °÷ ÀÖÀ» °æ¿ì
                     }
                  }
                //¾ÆÀÌµð¸¦ ÀÔ·Â ¾ÈÇßÀ» °æ¿ì
                if(s.replaceAll(" ", "").equals("")) {
                	JOptionPane.showMessageDialog(null, "°ø¹é¸¸ ÀÔ·ÂµÇ¾ú½À´Ï´Ù", "ÀÔ·Â ¿À·ù", JOptionPane.ERROR_MESSAGE);
                }
                //¾ÆÀÌµð¿¡ °ø¹é È¤Àº Æ®¼ö¹®ÀÚ°¡ Æ÷ÇÔµÈ °æ¿ì
                else if(!Pattern.matches(pattern, s)) {
                	JOptionPane.showMessageDialog(null, "¾ÆÀÌµð¿¡ °ø¹é È¤Àº Æ¯¼ö¹®ÀÚ°¡ ÀÔ·ÂµÇ¾ú½À´Ï´Ù", "ÀÔ·Â ¿À·ù", JOptionPane.ERROR_MESSAGE);
                }
                //¾ÆÀÌµð Áßº¹µÇ¾úÀ» °æ¿ì
                else if(overlapCheck) {
               	 JOptionPane.showMessageDialog(null, "¾ÆÀÌµð°¡ Áßº¹µÇ¾ú½À´Ï´Ù", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
               	 overlapCheck = false;	  //false ÇÑ ÀÌÀ¯´Â ´Ù½Ã ¾ÆÀÌµð¸¦ °íÄ£ ´ÙÀ½ Áßº¹È®ÀÎÀ» ´­·¯¼­ °Ë»ç¸¦ ÇØ¾ßÇÏ±â ¶§¹®
               }
                else {
                	JOptionPane.showMessageDialog(null, "Áßº¹µÈ ¾ÆÀÌµð°¡ ¾ø½À´Ï´Ù");
                	idcheck.setEnabled(false); 	//¹öÆ° ºñÈ°¼ºÈ­
                	idField.setEditable(false);
                	idcheckcheck = false;
                }
              }catch(Exception e1) {}
           }
        });
        
        //È¸¿ø°¡ÀÔ »ý¼º ¹öÆ°(create) ÀÌº¥Æ®
        this.create.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				user_ID = idField.getText();
				user_PASSWORD = new String(passField.getPassword());
				user_PASSRE = new String(passCheckField.getPassword());
				
				//ºñ¹Ð¹øÈ£°¡ µ¿ÀÏÇÏÁö ¾ÊÀ» °æ¿ì
				if (!user_PASSWORD.equals(user_PASSRE)) {													
					JOptionPane.showMessageDialog(null, "ºñ¹Ð¹øÈ£°¡ ¼­·Î ¸ÂÁö ¾Ê½À´Ï´Ù", "ºñ¹Ð¹øÈ£ ¿À·ù", 1);	

				}
				//Á¤º¸ ÀÔ·Â Á¦´ë·Î ¾ÈÇßÀ» °æ¿ì
				else if(user_ID.equals("") || user_PASSWORD.equals("")) {
					JOptionPane.showMessageDialog(null, "Á¤º¸ ÀÔ·ÂÀ» Á¦´ë·Î ÇØÁÖ¼¼¿ä!", "ÀÔ·Â¿À·ù", 1);
				}
				//id Áßº¹È®ÀÎÀ» ¾ÈÇßÀ» °æ¿ì
				else if(idcheckcheck) {
					JOptionPane.showMessageDialog(null, "¾ÆÀÌµð Áßº¹È®ÀÎÀ» ÇØÁÖ¼¼¿ä!", "Áßº¹È®ÀÎ", 1);
				}
				else {																				
					try {
						String sql = "insert into user values (?, ?, ?)";
						PreparedStatement pstmt;
						
						pstmt = db.getPstmt(sql);
						pstmt.setInt(1, 0);
		                pstmt.setString(2, user_ID);
		                pstmt.setString(3, user_PASSWORD);
		                pstmt.executeUpdate();
		                JOptionPane.showMessageDialog(null, "È¸¿ø °¡ÀÔ ¿Ï·á!", "È¸¿ø°¡ÀÔ", 1);
																								
		                setVisible(false);			//¸ÞÀÎ È­¸éÀ¸·Î µ¹¾Æ°¨
		                new Main();
					} catch (SQLException e1) {
						System.out.println("¿À·ù");
					}
				}
			}
		});
        //back ¹öÆ° ÀÌº¥Æ®(MainÈ­¸éÀ¸·Î °¡´Â)
        this.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               new Main();
            }
        });   
    }
}

