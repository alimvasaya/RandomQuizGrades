package quiz.autoquizgradesgenrator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

class Start                                        //Main class
{


    static int paramFlag=0,fillFlag=0;              //Int variables for illegal menu selections

    int quizes[][],students[],studentData[][];      //Int variables number of students, quizzes, and ID data

    int numOfStudent,numOfQuiz;                     //Int variables for calculations
    Result frame1 = new Result(null);
    private JFrame frame;                           //GUI frame

    public static void main(String[] args)          //Main method
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Start window = new Start();
                    window.frame.setVisible(true);      //Opens GUI frame upon running
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    public Start()
    {
        initialize();                                                     //Initialize frame contents
    }
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 626, 228);                              //Frame boundaries
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Select Your Option");            //GUI menu header
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));          //Font for text display
        lblNewLabel.setBounds(223, 25, 176, 36);                          //Label boundaries
        frame.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBounds(0, 139, 608, 41);                                 //Panel Boundaries
        frame.getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 5, 0, 0));                      //Grid layout for buttons on panel

        JButton btnNewButton_4 = new JButton("Help");                     //First button on grid, help button
        btnNewButton_4.addActionListener(new ActionListener()             //Action listener to respond to user click
        {
            public void actionPerformed(ActionEvent e)                     //Action performed upon user click
            {
                JOptionPane.showMessageDialog(frame,"Read this carefully to understand the application: \n\n*This program uses an array to calculate and display student ID, random quiz scores, and data ranges for each quiz. \n*The Set Params option will ask for # of students and # of quizzes. \n*The Fill Array option will generate the random numbers from your input. \n*The Display Results option will display the randomly generated values. \n*You can quit the application at any point by choosing the Quit option. \n\nNOTE: You must set parameters before filling the array, and you must fill the array before displaying results","Help", JOptionPane.INFORMATION_MESSAGE);
            }                                                              //JOptionPane displays instructions upon clicking help button
        });
        panel.add(btnNewButton_4);                                        //Adds help button to panel

        JButton btnNewButton_3 = new JButton("Set Params");               //Second button on grid, set parameters button
        btnNewButton_3.addActionListener(new ActionListener()             //Action listener to respond to user click
        {
            public void actionPerformed(ActionEvent e)                     //Action performed upon user click
            {
                paramFlag=1;                                                //Value that the fill array action calls back to ensure parameters are set before filling array

                String studnet = JOptionPane.showInputDialog(frame, "Enter Number of Students");     //First display to prompt user for number of students
                String quiz = JOptionPane.showInputDialog(frame, "Enter Number of Quizzes");          //Second display to prompt user for number of quizzes
                numOfStudent=Integer.parseInt(studnet);
                numOfQuiz= Integer.parseInt(quiz);                                                   //Holds numerical values entered by user
            }
        });
        panel.add(btnNewButton_3);                                        //Adds set params button to panel

        JButton btnNewButton_2 = new JButton("Fill Array");               //Third button on grid, fill array button
        btnNewButton_2.addActionListener(new ActionListener()             //Action listener to respond to user click
        {
            public void actionPerformed(ActionEvent e)                     //Action performed upon user click
            {

                if(paramFlag==1)                                            //if statement to ensure parameters are set before filling array
                {
                    fillFlag=1;                                              //Value that the display results action calls back to ensure array is filled
                    students=new int[numOfStudent];

                    quizes=new int[numOfQuiz][numOfStudent];

                    studentData=new int[numOfStudent][numOfQuiz];

                    Random r = new Random();

                    int studentId=75678;

                    for(int i=0;i<numOfStudent;i++,studentId++)              //Calculations for array given from the data parameters
                    {
                        students[i]=studentId;
                    }
                    for(int i=0;i<numOfStudent;i++)
                    {
                        for(int j=0;j<numOfQuiz;j++)
                        {
                            studentData[i][j]=r.nextInt(100);
                        }
                    }
                    for(int i=0;i<numOfQuiz;i++)
                    {
                        for(int j=0;j<numOfStudent;j++)
                        {
                            quizes[i][j]=studentData[j][i];
                        }
                    }
                    JOptionPane.showMessageDialog(frame,"Array Filled","Result", JOptionPane.INFORMATION_MESSAGE);     //JOptionPane to confirm array has been filled
                }
                else                                                        //Else statement to handle user action before parameters are set
                {
                    JOptionPane.showMessageDialog(frame,"Set Params First","Result", JOptionPane.INFORMATION_MESSAGE); //JOptionPane informing user to set parameters before filling array
                }
            }
        });
        panel.add(btnNewButton_2);                                        //Adds fill array button to panel

        JButton btnNewButton_1 = new JButton("Display Results");          //Fourth button on grid, display results button
        btnNewButton_1.addActionListener(new ActionListener()             //Action listener to respond to user click
        {
            public void actionPerformed(ActionEvent e)                     //Action performed upon user click
            {

                if(fillFlag==1)                                             //if statement to ensure array is filled before displaying results
                {
                    String display="";
                    display="ID\t";                                          //Displays table for student ID, quiz, and calculated values for each student and quiz

                    for(int i=0;i<numOfQuiz;i++)
                    {
                        display =display+"Q#"+(i+1)+"\t";
                    }
                    display =display+"\n";
                    for(int i=0;i<numOfStudent;i++)
                    {
                        display =display+students[i]+"\t";
                        for(int j=0;j<numOfQuiz;j++)
                        {
                            display =display+studentData[i][j]+"\t";           //Layout for array results in display results window
                        }
                        display =display+"\n";
                    }
                    display =display+"\n";
                    for(int i=0;i<numOfQuiz;i++)
                    {
                        Arrays.sort(quizes[i]);
                    }
                    int avg[]=new int[numOfQuiz];
                    for(int i=0;i<numOfQuiz;i++)
                    {
                        int sum=0;
                        for(int j=0;j<numOfStudent;j++)
                        {
                            sum+=quizes[i][j];
                        }
                        avg[i]=sum/numOfStudent;
                    }
                    for(int i=0;i<numOfQuiz;i++)
                    {
                        display =display+"Quiz: #"+(i+1)+"\n";                         //Displays quiz #
                        display =display+"Lowest: "+quizes[i][0]+"\n";                 //Displays lowest score
                        display =display+"Highest: "+quizes[i][numOfStudent-1]+"\n";   //Displays highest score
                        display =display+"Medium: "+quizes[i][numOfStudent/2]+"\n";    //Displays medium score
                        display =display+"Average: "+avg[i]+"\n\n";                    //Displays average of scores
                    }


                    frame1 = new Result(display);
                    frame1.setVisible(true);                                          //Opens frame for display results

                }
                else                                                                 //Else statement to handle user action before array is filled
                {
                    JOptionPane.showMessageDialog(frame,"Set Params First & Fill Array" ,"Result", JOptionPane.INFORMATION_MESSAGE);    //JOptionPane informing user to complete required tasks before displaying results
                }

            }
        });
        panel.add(btnNewButton_1);                                                 //Adds display results button to panel

        JButton btnNewButton = new JButton("Quit");                                //Fifth button, Quit program button
        btnNewButton.addActionListener(new ActionListener()                        //Action listener to respond to user click
        {
            public void actionPerformed(ActionEvent e)                              //Action performed upon user click
            {
                frame.dispose();
                frame1.dispose();                                                       //terminates GUI frame
            }
        });
        panel.add(btnNewButton);                                                   //Adds quits button to panel
    }

}
class Result extends JFrame                                                      //Results class to handle frame display for calculated values
{
    private JPanel contentPane;
    JTextPane textPane;                                                           //Panel for display results window

    public Result(String x)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                            //Terminates window upon user click
        setBounds(100, 100, 844, 433);                                             //Display results panel boundaries
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));                        //Display results panel border
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();                                //Scroll ability and scroll boundaries for display results window
        scrollPane.setBounds(5, 5, 816, 308);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane);                                               //Adds scroll ability and constant display of scroll bar

        textPane = new JTextPane();
        textPane.setText(x);

        scrollPane.setViewportView(textPane);

        JButton btnNewButton = new JButton("Ok");                                  //Ok button allowing for termination of display results window
        btnNewButton.addActionListener(new ActionListener()                        //Action listener to respond to user click
        {
            public void actionPerformed(ActionEvent arg0)                           //Action performed upon user click
            {
                dispose();                                                           //Terminates display results window upon clicking ok
            }
        });
        btnNewButton.setBounds(367, 337, 97, 25);                                  //Ok button boundaries
        contentPane.add(btnNewButton);                                             //Adds ok button to panel
    }
}