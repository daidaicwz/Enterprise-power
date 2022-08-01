package enterprise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class enterprise_info extends JFrame {
    private JLabel etpname;
    private JLabel c1,c2,c3,c4,c5;
    private JButton b1,b2,b3,b4;
    private JTextField t1,t2,t3,t4,t5;
    private JTextField search;
    private String etpname1;

    public enterprise_info(){
        super("enterprise_info");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        etpname = new JLabel("输入企业名：",JLabel.RIGHT);
        c1 = new JLabel("企业编号",JLabel.RIGHT);
        c2 = new JLabel("企业名称",JLabel.RIGHT);
        c3 = new JLabel("企业地址",JLabel.RIGHT);
        c4 = new JLabel("联系电话",JLabel.RIGHT);
        c5 = new JLabel("联系人",JLabel.RIGHT);

        b1 = new JButton("查询");
        b2 = new JButton("增加");
        b3 = new JButton("删除");
        b4 = new JButton("修改");

        t1 = new JTextField(200);
        t2 = new JTextField(200);
        t3 = new JTextField(200);
        t4 = new JTextField(200);
        t5 = new JTextField(200);
        search = new JTextField(200);

        etpname.setBounds(80,50,100,30);
        c1.setBounds(80,100,100,30);
        c2.setBounds(80,130,100,30);
        c3.setBounds(80,160,100,30);
        c4.setBounds(80,190,100,30);
        c5.setBounds(80,220,100,30);
        search.setBounds(200,50,100,25);
        b1.setBounds(370,52,60,25);
        b2.setBounds(200,300,60,25);
        b3.setBounds(300,300,60,25);
        b4.setBounds(400,300,60,25);

        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);
        t3.setBounds(200,160,350,30);
        t4.setBounds(200,190,350,30);
        t5.setBounds(200,220,350,30);

        ButtonActionPerformed buttoner = new ButtonActionPerformed();
        ButtonActionPerformed2 butoner2 = new ButtonActionPerformed2();
        ButtonActionPerformed3 buttoner3 = new ButtonActionPerformed3();
        ButtonActionPerformed4 buttoner4 = new ButtonActionPerformed4();

        b1.addActionListener(buttoner);
        b4.addActionListener(butoner2);
        b3.addActionListener(buttoner3);
        b2.addActionListener(buttoner4);

        container.add(etpname);
        container.add(c1);
        container.add(c2);
        container.add(c3);
        container.add(c4);
        container.add(c5);
        container.add(search);
        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(b4);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private class ButtonActionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            etpname1 = search.getText();
            String dirver = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dirver);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            Statement stmt;
            ResultSet rs;
            String database = "jdbc:mysql://localhost:3306/enterprise?"
                    +"useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
            String username = "root";
            String password = "123456";
            Connection con = null;
            try {
                con = DriverManager.getConnection(database,username,password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                stmt = (Statement) con.createStatement();
                rs = (ResultSet) stmt.executeQuery("select * from enterprise_info");
                while (rs.next()){
                    if (rs.getString(2).equals(etpname1)){
                        t1.setText(rs.getString(1));
                        t2.setText(rs.getString(2));
                        t3.setText(rs.getString(3));
                        t4.setText(rs.getString(4));
                        t5.setText(rs.getString(5));
                    }
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    private class ButtonActionPerformed2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enterprise_info_update app6 = new enterprise_info_update();
            app6.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enterprise_info_delete app5 = new enterprise_info_delete();
            app5.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enterprise_info_insert app4 = new enterprise_info_insert();
            app4.setLocationRelativeTo(null);
        }
    }



    public static void main(String[] args){
        enterprise_info app = new enterprise_info();
        app.setLocationRelativeTo(null);
    }
}
