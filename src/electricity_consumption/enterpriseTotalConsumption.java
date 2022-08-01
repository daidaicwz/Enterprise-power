package electricity_consumption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class enterpriseTotalConsumption extends JFrame {
    private JLabel etpname ;
    private JLabel l1,l2;
    private JTextField search;
    private JButton b1;
    private JTextField t1,t2;
    private String etpname1;
    private int TolPeak ,TolValley ;

    public enterpriseTotalConsumption(){
        super("查询企业总的峰电量和谷电量");
        setSize(700, 400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        Container container = getContentPane();
        etpname = new JLabel("输出入要查询的企业名：",JLabel.LEFT);
        l1 = new JLabel("总峰电量",JLabel.RIGHT);
        l2 = new JLabel("总谷电量",JLabel.RIGHT);
        b1 = new JButton("查询");
        t1 = new JTextField(100);
        t2 = new JTextField(100);
        search = new JTextField(100);

        etpname.setBounds(70,50,200,30);
        l1.setBounds(80,100,100,30);
        l2.setBounds(80,130,100,30);
        search.setBounds(200,50,100,25);
        b1.setBounds(370,52,60,25);
        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);

        ButtonActionPerformed btn1 = new ButtonActionPerformed();
        b1.addActionListener(btn1);

        container.add(etpname);
        container.add(l1);
        container.add(l2);
        container.add(t1);
        container.add(t2);
        container.add(search);
        container.add(b1);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private class ButtonActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            etpname1 = search.getText();
            String dirver = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dirver);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            String database = "jdbc:mysql://localhost:3306/enterprise?"
                    +"useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
            String username = "root";
            String password = "123456";
            Connection con = null;
            Statement stmt;
            ResultSet rs;
            try {
                con = DriverManager.getConnection(database,username,password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                stmt = (Statement) con.createStatement();
                rs = (ResultSet) stmt.executeQuery("select PeakPower,ValleyPower from " +
                        "electricity_consumption where Etpname='"+etpname1+"'");
                while (rs.next()){
                    TolPeak = rs.getInt(1)+TolPeak;
                    TolValley = rs.getInt(2)+TolValley;
                }
                t1.setText(""+TolPeak);
                t2.setText(""+TolValley);
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        enterpriseTotalConsumption app = new enterpriseTotalConsumption();
        app.setLocationRelativeTo(null);
    }

}
