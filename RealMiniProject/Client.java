package RealMiniProject;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static MainFrame frame;
    public static ObjectInputStream inputStream;
    public static ObjectOutputStream outputStream;
    public static <pd> void main(String[] args){
        frame = new MainFrame();
        frame.setVisible(true);
        try {
            Socket socket = new Socket("127.0.0.1", 2000);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            ListStudent.btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Client.frame.menu.setVisible(true);
                    Client.frame.listStudent.setVisible(false);

                    Client.frame.repaint();
                }
            });

            MainMenu.list.addActionListener(new ActionListener() {
                ArrayList<Students> incomeSt = new ArrayList<>();

                @Override
                public void actionPerformed(ActionEvent e) {
                    PackageData pd = new PackageData();
                    pd.setOperationType("LIST");
                    incomeSt = getStudents(pd);

                    Client.generateTable(incomeSt);

                    Client.frame.menu.setVisible(false);
                    Client.frame.addStudent.setVisible(false);
                    Client.frame.listStudent.setVisible(true);
                    Client.frame.listStudent.table.updateUI();
                }
            });

            AddStudent.btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Client.frame.menu.setVisible(true);
                    Client.frame.addStudent.setVisible(false);

                    Client.frame.repaint();
                }
            });

            AddStudent.btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(AddStudent.textName.getText().length() > 0 && AddStudent.textSurname.getText().length() > 0 && AddStudent.textAge.getText().length() > 0){
                        PackageData pd = new PackageData();
                        pd.setOperationType("ADD");
                        pd.student = new Students();
                        pd.students = null;
                        pd.student.setName(AddStudent.textName.getText());
                        pd.student.setSurname(AddStudent.textSurname.getText());
                        System.out.println(AddStudent.textAge.getText());
                        try{
                            if (AddStudent.textAge.getText() != null){
                                pd.student.setAge(Integer.parseInt(AddStudent.textAge.getText()));
                            }
                        }catch (NumberFormatException e1){
                            pd.student.setAge(0);
                        }
                        addStudent(pd);
                        System.out.println(pd + "");
                        AddStudent.textName.setText("");
                        AddStudent.textSurname.setText("");
                        AddStudent.textAge.setText("");

                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void addStudent(PackageData pd){
        try{
            outputStream.writeObject(pd);
        }catch (Exception e){

        }
    }

    public static ArrayList<Students> getStudents(PackageData pd){
        ArrayList<Students> students = new ArrayList<>();
        try{
            outputStream.writeObject(pd);
            pd = (PackageData) inputStream.readObject();
            students = pd.getStudents();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
    public static void generateTable(ArrayList<Students> students){
        String header[] = {"ID", "Name","Surname","Age"};
        Object data[][] = new Object[students.size()][4];
        for(int i =0;i<students.size();i++){
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getSurname();
            data[i][3] = students.get(i).getAge();
            System.out.println(data[i][0]);
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        Client.frame.listStudent.table.setModel(model);
        Client.frame.repaint();
    }
}