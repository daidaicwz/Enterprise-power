package electricity_consumption;

import enterprise.enterprise_info;
import enterprise.enterprise_info_update;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class electricity_consumption_info extends JFrame {
    private JLabel l1,l2,l3,l4,l5,l6,l7;
    private JLabel name;
    private JButton b1,b2,b3,b4,b5,b6,b7;
    private JTextField search;
    private JTextField t1,t2,t3,t4,t5,t6,t7;
    private String name1;

    public electricity_consumption_info(){
        super("electricity_consumption_info");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        name = new JLabel("输入企业名：",JLabel.RIGHT);
        l1 = new JLabel("企业编号",JLabel.RIGHT);
        l2 = new JLabel("企业名称",JLabel.RIGHT);
        l3 = new JLabel("峰用电量",JLabel.RIGHT);
        l4 = new JLabel("谷用电量",JLabel.RIGHT);
        l5 = new JLabel("总用电量",JLabel.RIGHT);
        l6 = new JLabel("用电月份",JLabel.RIGHT);
        l7 = new JLabel("电费",JLabel.RIGHT);

        b1 = new JButton("查询");
        b2 = new JButton("增加");
        b3 = new JButton("删除");
        b4 = new JButton("修改");
        b5 = new JButton("查看用电比例");
        b6 = new JButton("查看峰谷总电量");
        b7 = new JButton("查看年电量花费");

        t1 = new JTextField(200);
        t2 = new JTextField(200);
        t3 = new JTextField(200);
        t4 = new JTextField(200);
        t5 = new JTextField(200);
        t6 = new JTextField(200);
        t7 = new JTextField(200);
        search = new JTextField(200);

        name.setBounds(80,50,100,30);
        l1.setBounds(80,100,100,30);
        l2.setBounds(80,130,100,30);
        l3.setBounds(80,160,100,30);
        l4.setBounds(80,190,100,30);
        l5.setBounds(80,220,100,30);
        l6.setBounds(80,250,100,30);
        l7.setBounds(80,280,100,30);
        search.setBounds(200,50,100,25);
        b1.setBounds(370,52,60,25);
        b2.setBounds(200,320,60,25);
        b3.setBounds(300,320,60,25);
        b4.setBounds(400,320,60,25);
        b5.setBounds(550,100,120,50);
        b6.setBounds(550,160,120,50);
        b7.setBounds(550,220,120,50);
        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);
        t3.setBounds(200,160,350,30);
        t4.setBounds(200,190,350,30);
        t5.setBounds(200,220,350,30);
        t6.setBounds(200,250,350,30);
        t7.setBounds(200,280,350,30);

        ButtonActionPerformed buttoner = new ButtonActionPerformed();
        ButtonActionPerformed2 buttoner2 = new ButtonActionPerformed2();
        ButtonActionPerformed3 buttoner3 = new ButtonActionPerformed3();
        ButtonActionPerformed4 buttoner4 = new ButtonActionPerformed4();
        ButtonActionPerformed5 buttoner5 = new ButtonActionPerformed5();
        ButtonActionPerformed6 buttoner6 = new ButtonActionPerformed6();
        ButtonActionPerformed7 buttoner7 = new ButtonActionPerformed7();

        b1.addActionListener(buttoner);
        b2.addActionListener(buttoner3);
        b3.addActionListener(buttoner2);
        b4.addActionListener(buttoner4);
        b5.addActionListener(buttoner5);
        b6.addActionListener(buttoner6);
        b7.addActionListener(buttoner7);

        container.add(name);
        container.add(l1);
        container.add(l2);
        container.add(l3);
        container.add(l4);
        container.add(l5);
        container.add(l6);
        container.add(l7);
        container.add(search);
        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(b4);
        container.add(b5);
        container.add(b6);
        container.add(b7);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        container.add(t6);
        container.add(t7);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private class ButtonActionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            name1 = search.getText();
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
                rs = (ResultSet) stmt.executeQuery("select * from electricity_consumption");
                while (rs.next()){
                    if (rs.getString(2).equals(name1)){
                        t1.setText(rs.getString(1));
                        t2.setText(rs.getString(2));
                        t3.setText(rs.getString(3));
                        t4.setText(rs.getString(4));
                        t5.setText(rs.getString(5));
                        t6.setText(rs.getString(6));
                        t7.setText(rs.getString(7));
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
            elect_consumption_delete app7 = new elect_consumption_delete();
            app7.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            elect_consumption_insert app8 = new elect_consumption_insert();
            app8.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            elect_consumption_update app9 = new elect_consumption_update();
            app9.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed5 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Proportion app21 = new Proportion();
            app21.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed6 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enterpriseTotalConsumption app22 = new enterpriseTotalConsumption();
            app22.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed7 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            searchByMonth app23 = new searchByMonth();
            app23.setLocationRelativeTo(null);
        }
    }


    public static void main(String[] args){
        electricity_consumption_info app = new electricity_consumption_info();
        app.setLocationRelativeTo(null);
    }
}
