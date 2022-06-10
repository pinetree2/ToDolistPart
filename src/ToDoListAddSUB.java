import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ToDoListAddSUB {
    private static JFrame frame = new JFrame("ToDoListAdd");
    private JButton addBtn;
    private JTextField task;
    private JPanel addTask;
    private JPanel jpCald;
    private JSpinner m_Idx;

    private static String Task;
    private static String deadline;
    private static Date deadlineDate;
    private static int M_Idx;

    Calendar cld = Calendar.getInstance();
    JDateChooser selectedDate = new JDateChooser(cld.getTime());

    public void run() {
        frame.setContentPane(new ToDoListAddSUB().addTask);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ToDoListAddSUB(){
        frame.setContentPane(addTask);
        frame.setLocationRelativeTo(null);

        selectedDate.setDateFormatString("yyyy-MM-dd");
        jpCald.add(selectedDate);

        //버튼 누르면 서버에 요청하고 db에 추가
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ListDataMain> datamainlist = ToDoListBring.bringMain();

                int main_index;
                int sub_index;
                int main_big = 0;
                int sub_big =0;
                String msg;
                int chat_index = 1;

                Task = task.getText();
                deadline = ((JTextField) selectedDate.getDateEditor().getUiComponent()).getText();
                M_Idx = (Integer)m_Idx.getValue();
                //이외 db구성 항목들 추가해야함
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String today = dateFormat.format(new Date());
                //Date deadlineDate = null;

                try {
                    deadlineDate = new Date(dateFormat.parse(deadline).getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Date todayDate = null;
                try {
                    todayDate = new Date(dateFormat.parse(today).getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                int compare = deadlineDate.compareTo(todayDate);
                if (Task .isEmpty())
                    JOptionPane.showMessageDialog(null, "일정 내용을 입력하세요");
                else if (compare < 0)
                    JOptionPane.showMessageDialog(null, "이미 지난 날짜입니다.");
                //else if 문 하나 더 넣어서 유효한 m_Idx인지 검사, getM_Idx()하면댐

                else {

                    for(int i = 0; i< datamainlist.size(); i++) {
                        main_index = datamainlist.get(i).M_idx;
                        sub_index = datamainlist.get(i).S_idx;

                        if(main_big < main_index){
                            main_big = main_index;
                        }
                        if(sub_big < sub_index){
                            sub_big = sub_index;
                        }
                    }
                    sub_big +=1;
                    if(M_Idx > main_big){
                        JOptionPane.showMessageDialog(null, "유효하지 않은 번호입니다");
                    }
                    else{
                        try {
                            ToDoListAddSubController.toDoListAddsubController(M_Idx,sub_big,Task,deadlineDate,chat_index);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }

                    frame.dispose();
                    frame.setVisible(false);
                    //클라에서 리턴있을때 종료시킬수있음
                }
            }
        });
//JOptionPane.showMessageDialog(null, "일정 내용을 입력하세요");
    }

    //db에 넘길 리턴값들, 이 메소드들 써서 값 저장해서 db로 넘기면댐, 이외 db구성 항목들 추가해야함 //deadline은 String이랑 Date중 필요한 형식 사용
    //창 하나만 띄운다고 가정하고 static으로 만들어뒀는데 나중에 창 여러개띄워야한다면 수정필요함
    public String getTask(){
        return this.Task;
    }
    public String getDeadline(){
        return this.deadline;
    }
    public Date getDeadlineDate(){
        return this.deadlineDate;
    }
    public int getM_Idx() { return this.M_Idx; }


}
