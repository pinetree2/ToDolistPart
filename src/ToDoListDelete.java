import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListDelete {
    private static JFrame frame = new JFrame("ToDoListDelete");
    private JButton button1;
    private JSpinner m_Idx;
    private JSpinner s_Idx;
    private JPanel deleteToDo;

    private static int M_Idx;
    private static int S_Idx;
    public void run() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ToDoListDelete(){
        frame.setContentPane(deleteToDo);
        frame.setLocationRelativeTo(null);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                M_Idx = (Integer)m_Idx.getValue();
                S_Idx = (Integer)s_Idx.getValue();
                frame.dispose();
                frame.setVisible(false);
                System.out.println(getM_Idx());
                System.out.println(getS_Idx());
            }
        });
    }

    public int getM_Idx() { return this.M_Idx; }
    public int getS_Idx() { return this.S_Idx; }

}
