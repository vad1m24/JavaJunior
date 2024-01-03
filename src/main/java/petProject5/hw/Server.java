package petProject5.hw;

import lombok.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


/**
 * 1. Если в начале сообщения есть '@4' - то значит отсылаем сообщеине клиенту с идентификатором 4.
 * 2. Если в начале сообщения нет '@' - значит, это сообщение нужно послать остальным клиентам.
 */

public class Server {

    // message broker (kafka, redis, rabbitmq, ...)
    // client sent letter to broker

    // server sent to SMTP-server

    public static final int PORT = 8181;

    private static long clientIdCounter = 1L;
    private static Map<Long, SocketWrapper> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);
            while (true) {
                final Socket client = server.accept();
                final long clientId = clientIdCounter++;

                SocketWrapper wrapper = new SocketWrapper(clientId, client);
                System.out.println("Подключился новый клиент[" + wrapper + "]");
                clients.put(clientId, wrapper);

                new Thread(() -> {
                    try (Scanner input = wrapper.getInput(); PrintWriter output = wrapper.getOutput()) {
                        output.println("Подключение успешно. Список всех клиентов: " + clients);

                        while (true) {
                            String clientInput = input.nextLine();
                            if (Objects.equals("q", clientInput)) {
                                clients.remove(clientId);
                                clients.values().forEach(it -> it.getOutput().println("Клиент[" + clientId + "] отключился"));
                                break;
                            }

                            // формат сообщения: "цифра сообщение"
                            if (clientInput.substring(0, 1).equals("@")) {
                                long destinationId = Long.parseLong(clientInput.substring(1, 2));
                                SocketWrapper destination = clients.get(destinationId);
                                destination.getOutput().println(clientInput);
                            } else {
                                long notDestinationId = wrapper.getId();
                                for (Map.Entry<Long, SocketWrapper> clientDestination : clients.entrySet()) {
                                    if (clientDestination.getKey() != notDestinationId) {
                                        SocketWrapper destination = clients.get(clientDestination.getKey());
                                        destination.getOutput().println(clientInput);
                                    }
                                }
                            }
                        }
                    }
                }).start();
            }
        }
    }
}

@Data
class SocketWrapper implements AutoCloseable {

    private final long id;
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;

    SocketWrapper(long id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    @Override
    public String toString() {
        return String.format("%s", socket.getInetAddress().toString());
    }
}