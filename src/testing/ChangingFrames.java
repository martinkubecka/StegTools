package testing;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

public class ChangingFrames extends JFrame{
    private JPanel panel1, panel2;
    private JButton but,but2;
    public ChangingFrames()
    {
        createPanel();
        addPanel();
    }
    private void createPanel()
    {
        panel1 = new JPanel();
        but = new JButton("TestButton");
        but.addActionListener(new addButtonListener());
        but.setBounds(50, 90, 190, 30);//There are example values but remember about setting size
        panel2 = new JPanel();
        but2 = new JButton("TestButton2");
        but2.setBounds(50, 50, 90, 30);//There are example values but remember about setting size
    }
    private void addPanel()
    {
        panel1.add(but);
        panel2.add(but2);
        add(panel1);
    }

    class addButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            getContentPane().removeAll();
            getContentPane().add(panel2);//Adding to content pane, not to Frame
            repaint();
            printAll(getGraphics());//Extort print all content
        }
    }

    public static void main(String args[])
    {
        ChangingFrames frame = new ChangingFrames();
        frame.setTitle("Test Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }

}
