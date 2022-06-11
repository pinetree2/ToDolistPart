import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ToDoListDeleteController {

    //메인 인덱스
    //서브 인덱스 0이면
    //해당하는 메인인덱스를 db에서 지우도록

    public static void deleteController(int M_idx, int S_idx) throws IOException {
        int m_idx = M_idx;
        int s_idx = S_idx;
        String msg;

        String port = "7777";
        BufferedReader in;
        Socket socket = new Socket();
        OutputStream out;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = socket.getOutputStream();
        socket = new Socket("127.0.0.1", 7777);

        msg ="700";
        try {
            //메시지,메인인덱스, 채팅방인덱스, 체크
            out.write((msg + "|" + String.valueOf(m_idx) + "|" + String.valueOf(s_idx) + "\n").getBytes());
            in.close();
            out.close();
        } catch (IOException e) {
        }
    }

    //서버에서 메시지 받아야해 삭제하는거

}
