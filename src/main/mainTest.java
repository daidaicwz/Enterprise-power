package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import price.*;
import electricity_consumption.*;
import enterprise.*;


public class mainTest extends JFrame {
    private JButton b1,b2,b3;
    private JLabel weText;

    public mainTest(){
        super("企业用电管理系统");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        weText = new JLabel("欢迎使用企业用电管理系统",JLabel.RIGHT);
        b1 = new JButton("企业信息管理");
        b2 = new JButton("用电信息管理");
        b3 = new JButton("电价信息管理");

        weText.setBounds(100,80,300,35);
        b1.setBounds(100,210,150,50);
        b2.setBounds(260,210,150,50);
        b3.setBounds(420,210,150,50);

        ButtonActionPerformed btn1 = new ButtonActionPerformed();
        ButtonActionPerformed2 btn2 = new ButtonActionPerformed2();
        ButtonActionPerformed3 btn3 = new ButtonActionPerformed3();

        b1.addActionListener(btn2);
        b2.addActionListener(btn3);
        b3.addActionListener(btn1);

        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(weText);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private class ButtonActionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            price_info app11 =new  price_info();
            app11.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enterprise_info app12 =new enterprise_info();
            app12.setLocationRelativeTo(null);
        }
    }

    private class ButtonActionPerformed3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            electricity_consumption_info app13 =new electricity_consumption_info();
            app13.setLocationRelativeTo(null);
        }
    }

    public static void main(String[] args){
        mainTest app = new mainTest();
        app.setLocationRelativeTo(null);
    }

}
