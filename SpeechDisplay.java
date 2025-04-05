import java.awt.*;
import java.io.*;
import java.net.*;

public class SpeechDisplay extends Frame {
    private TextArea textArea;
    
    public SpeechDisplay() {
        setTitle("Speech Recognition Output");
        setSize(500, 300);
        setLayout(new BorderLayout());
        
        textArea = new TextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);
        
        setVisible(true);
        startServer();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    
                    String receivedText = in.readLine();
                    textArea.append(receivedText + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new SpeechDisplay();
    }
}
