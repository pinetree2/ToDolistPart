import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class ToDoList extends JList{
    private static JFrame toDoListFrame = new JFrame("ToDoList");
    private JButton button1;
    private JLabel toDoListLabel;
    private JPanel panel1;
    private JCheckBoxTree tree1;
    private JButton button2;
    private JButton button3;
    private JButton button;

    public static void run() {
        //frame.setContentPane(new ToDoList().panel1);
        toDoListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        toDoListFrame.pack();
        toDoListFrame.setVisible(true);
    }
    public static void refresh(JCheckBoxTree tree1) {


        ArrayList<ListDataMain> datamainlist = ToDoListBring.bringMain();
        ArrayList<ListDataSub> datasublist = ToDoListBring.bringSub();
        ListData rootdata = (new ListDataSub(1, 1, "To-do List", new Date(), 1, 1));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootdata);

        for (int i = 0; i < datamainlist.size(); i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(datamainlist.get(i));
            root.add(node);

            for (ListDataSub data : datasublist) {
                if (data.getM_idx() == datamainlist.get(i).getM_idx()) {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(data);
                    node.add(child);
                }
            }
        }

        tree1.setModel(new DefaultTreeModel(root), true);
        tree1.setBounds(12, 10, 135, 241);
    }

    public ToDoList() {
        panel1.setPreferredSize(new Dimension(480, 600));
        toDoListFrame.setContentPane(panel1);
        toDoListFrame.setLocationRelativeTo(null);

        //tree1 = new JTree();
        //tree1.setCellRenderer();
        ArrayList<ListDataMain> datamainlist = ToDoListBring.bringMain();
        ArrayList<ListDataSub> datasublist = ToDoListBring.bringSub();
        ListData rootdata = (new ListDataSub(1, 1, "To-do List", new Date(), 1, 1));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootdata);

        for(int i = 0; i< datamainlist.size(); i++){
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(datamainlist.get(i));
            root.add(node);
            button = new JButton("??????");
            for (ListDataSub data : datasublist) {
                if (data.getM_idx() == datamainlist.get(i).getM_idx()) {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(data);
                    node.add(child);
                }
            }
        }

        tree1.setModel( new DefaultTreeModel(root), true);
        tree1.setBounds(12, 10, 135, 241);

        // ????????? checkbox ???????????? ??? ?????????
        // ??????????????? ???????????? ??????, ??? ????????? ?????????????????? ?????? ?????? ????????? ???????????? ????????? ??????.
        // ????????? ?????????????????? ?????? ????????? ?????????.

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoListAddMAIN a = new ToDoListAddMAIN(tree1); a.run();
            }
        });

        tree1.addCheckChangeEventListener(new JCheckBoxTree.CheckChangeEventListener() {
            public void checkStateChanged(JCheckBoxTree.CheckChangeEvent event) {
                //System.out.print("checkbox click => ");
                TreePath[] paths = tree1.getCheckedPaths();
                for (TreePath tp : paths) {
                    for (Object pathPart : tp.getPath()) {
                        //System.out.print(pathPart + "\n");
                    }
                    //System.out.println();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoListAddSUB a = new ToDoListAddSUB(tree1); a.run();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoListDelete a = new ToDoListDelete(tree1); a.run();
            }
        });
    }

}
