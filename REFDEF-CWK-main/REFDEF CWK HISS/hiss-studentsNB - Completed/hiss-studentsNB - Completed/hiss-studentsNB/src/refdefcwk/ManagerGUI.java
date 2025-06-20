package refdefcwk; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the simulation
 * 
 * @author A.A.Marczyk
 * @version 20/05/25
 */
public class ManagerGUI 
{
    private HISS mg = new Manager("Mary",1000);
    private JFrame myFrame = new JFrame("Project GUI");
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton listBtn = new JButton("List all Staff");
    private JButton quitBtn = new JButton("Quit");
    private JButton hireBtn = new JButton("Hire Staff"); //students
    private JButton teamBtn = new JButton("List Team"); //students
    private JButton clearBtn = new JButton("Clear"); //students
    private JButton showBtn = new JButton("Show State"); //already present as "list projects"
    private JButton doJobBtn = new JButton("Do a Job"); // ADDED
    private JButton rejoinBtn = new JButton("Staff Rejoin from Leave"); // ADDED

    private JPanel eastPanel = new JPanel();

    public static void main(String[] args)
    {
        new ManagerGUI();
    }
    
    
    public ManagerGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
        listing.setVisible(true);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(8,1)); // expanded for new buttons
        eastPanel.add(listBtn);
        eastPanel.add(quitBtn);
        eastPanel.add(hireBtn); //students task 2.6
        eastPanel.add(teamBtn); //students
        eastPanel.add(doJobBtn); // ADDED
        eastPanel.add(rejoinBtn); // ADDED
        eastPanel.add(clearBtn);// students
        eastPanel.add(showBtn); //late demo

        listBtn.addActionListener(new ListHandler());
        quitBtn.addActionListener(new QuitHandler());
        hireBtn.addActionListener(new HireHandler()); //students
        teamBtn.addActionListener(new TeamHandler()); //students
        clearBtn.addActionListener(new ClearHandler());//students
        showBtn.addActionListener(new StateHandler()); //late demo

        doJobBtn.addActionListener(new DoJobHandler()); // ADDED
        rejoinBtn.addActionListener(new RejoinHandler()); // ADDED
        
        listBtn.setVisible(true);
        teamBtn.setVisible(true);
        hireBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        doJobBtn.setVisible(true); // ADDED
        rejoinBtn.setVisible(true); // ADDED
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Jobs");
        menubar.add(fileMenu);

        JMenuItem view = new JMenuItem("View State of project");
        view.addActionListener(new StateHandler());
        fileMenu.add(view);

        JMenuItem listJobsItem = new JMenuItem("List all Jobs"); //students
        listJobsItem.addActionListener(new ListJobsHandler());
        fileMenu.add(listJobsItem);

        JMenuItem doJobItem = new JMenuItem("Do a job"); // students
        doJobItem.addActionListener(new DoJobHandler());
        fileMenu.add(doJobItem);

        //demo
        JMenuItem all = new JMenuItem("See all staff");
        all.addActionListener(new ListHandler());
        fileMenu.add(all);

        //Extra for the future
        JMenuItem stf = new JMenuItem("Get Staff");
        stf.addActionListener(new StfHandler());
        fileMenu.add(stf);

        JMenuItem rejoinMenuItem = new JMenuItem("Staff Rejoin from Leave"); // ADDED
        rejoinMenuItem.addActionListener(new RejoinHandler());
        fileMenu.add(rejoinMenuItem);
    }

// Menu item handlers
    private class StateHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String result = mg.toString();
            listing.setText(result);
            listing.setVisible(true);
        }
    }
    private class ListJobsHandler implements ActionListener//student task 2.6
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = mg.getAllJobs();
            listing.setText(xx);
        }
    }
    
    private class DoJobHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String inputValue = JOptionPane.showInputDialog("Job no ?: ");
            try {
                int jbNo =  Integer.parseInt(inputValue);            
                String result = mg.doJob(jbNo);
                JOptionPane.showMessageDialog(myFrame,result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(myFrame,"Invalid job number.");
            }
        }
    }

    private class RejoinHandler implements ActionListener // ADDED
    {
        public void actionPerformed(ActionEvent e)
        { 
            String inputValue = JOptionPane.showInputDialog("Staff name to rejoin?: ");
            String result = mg.staffRejoinTeam(inputValue);
            JOptionPane.showMessageDialog(myFrame,result);
        }
    }
    
// Button handlers
    
    private class ListHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = mg.getAllAvailableStaff();
            listing.setText(xx);
        }
    }

    private class QuitHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int answer = JOptionPane.showConfirmDialog(myFrame,
                    "Are you sure you want to quit?", "Finish",
                    JOptionPane.YES_NO_OPTION);
            // closes the application
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0); //closes the application
            }
        }
    }
    private class HireHandler implements ActionListener //students task 2.6
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String inputValue = JOptionPane.showInputDialog("Staff name ?: ");
            result = mg.hireStaff(inputValue);
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }
        
    private class TeamHandler implements ActionListener //students task 2.6
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = mg.getTeam();
            listing.setText(xx);
        }
    }

    
    private class ClearHandler implements ActionListener //students task 2.6
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setText("");
        }
    }
    
   //Extra for the future
   private class StfHandler implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
           String result = "";
           String inputValue = JOptionPane.showInputDialog("Staff name ?: ");
           result = mg.getStaff(inputValue);
           JOptionPane.showMessageDialog(myFrame,result);
       }
   }
    
}
