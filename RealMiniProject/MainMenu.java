package RealMiniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends Container{

    public JButton add;
    public static JButton list;

    public MainMenu(){
        setSize(500,500);
        setLayout(null);

        add = new JButton("ADD STUDENT");
        add.setLocation(100,150);
        add.setSize(300,30);
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Client.frame.menu.setVisible(false);
                Client.frame.addStudent.setVisible(true);
                Client.frame.repaint();
            }
        });
        add(add);

        list = new JButton("LIST STUDENTS");
        list.setLocation(100,190);
        list.setSize(300,30);
        list.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Client.frame.menu.setVisible(false);
                Client.frame.listStudent.setVisible(true);
                Client.frame.repaint();
            }
        });
        add(list);

        JButton btn = new JButton("EXIT");
        btn.setLocation(100,230);
        btn.setSize(300,30);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        add(btn);

    }
}