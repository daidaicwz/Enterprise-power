package electricity_consumption;

import main.mainTest;
import price.price_info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Proportion extends JFrame {
    private JLabel l1,l2,l3;
    private JTextField t1,t2,t3;
    private String s1,s2,s3;
    private int sum=0 ;
    private int TotalPower = 0,TolPeak = 0 , TolValley= 0;
    private float pratio , vratio;

    public Proportion() {
        super("用电比例和总用电情况");
        setSize(700, 400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        Container container = getContentPane();
        l1 = new JLabel("该区域的峰用电比例为：", JLabel.RIGHT);
        l2 = new JLabel("该区域的谷用电比例为：", JLabel.RIGHT);
        l3 = new JLabel("该区域的总用电量为：", JLabel.RIGHT);
        t1 = new JTextField(100);
        t2 = new JTextField(100);
        t3 = new JTextField(100);

        l1.setBounds(80, 100, 200, 30);
        l2.setBounds(80, 130, 200, 30);
        l3.setBounds(80, 160, 200, 30);
        t1.setBounds(300, 100, 350, 30);
        t2.setBounds(300, 130, 350, 30);
        t3.setBounds(300, 160, 350, 30);

        container.add(l1);
        container.add(l2);
        container.add(l3);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String dirver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dirver);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        Statement stmt;
        ResultSet rs;
        String database = "jdbc:mysql://localhost:3306/enterprise?"
                + "useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
        String username = "root";
        String password = "123456";
        Connection con = null;
        try {
            con = DriverManager.getConnection(database, username, password);
            con.setAutoCommit(true);
            System.out.println("和数据库建立连接");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("select Charge from electricity_consumption");
            while (rs.next()) {
                sum = rs.getInt(1) + sum;
            }
            t3.setText(sum + "");

            rs = (ResultSet)stmt.executeQuery("select PeakPower,ValleyPower,TotalPower from electricity_consumption");
            while (rs.next()){
                TotalPower = rs.getInt(3)+TotalPower;
                TolPeak = rs.getInt(1)+TolPeak;
                TolValley = rs.getInt(2)+TolValley;
            }
            pratio = (float) TolPeak/TotalPower*100;
            vratio = (float) TolValley/TotalPower*100;
            t1.setText(pratio+"%");
            t2.setText(vratio+"%");

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }



    public static void main(String[] args){
        Proportion app = new Proportion();
        app.setLocationRelativeTo(null);
    }

}
