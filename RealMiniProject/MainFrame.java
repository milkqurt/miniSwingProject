package RealMiniProject;

import javax.swing.*;

public class MainFrame extends JFrame{

    public static AddStudent addStudent;
    public static ListStudent listStudent;
    public static MainMenu menu;

    public MainFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(null);

        menu = new MainMenu();
        menu.setLocation(0,0);
        add(menu);

        addStudent = new AddStudent();
        addStudent.setLocation(0,0);
        addStudent.setVisible(false);
        add(addStudent);

        listStudent = new ListStudent();
        listStudent.setLocation(0,0);
        listStudent.setVisible(false);
        add(listStudent);

        repaint();
    }

    public static MainMenu getMenu() {
        return menu;
    }

    public static void setMenu(MainMenu menu) {
        MainFrame.menu = menu;
    }

    public static AddStudent getAddStudent() {
        return addStudent;
    }

    public static void setAddStudent(AddStudent addStudent) {
        MainFrame.addStudent = addStudent;
    }

    public static ListStudent getListStudent() {
        return listStudent;
    }

    public static void setListStudent(ListStudent listStudent) {
        MainFrame.listStudent = listStudent;
    }
}