package services;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.ituhn.pemkom2.serial.SerialDataHandler;
import java.util.ArrayList;
import java.util.List;

public class SerialService {

    private static SerialService instance;
    private SerialPort activePort;

    private final List<SerialDataHandler<String>> handlers = new ArrayList<>();

    private SerialService() {
    }

    public static synchronized SerialService getInstance() {
        if (instance == null) {
            instance = new SerialService();
        }
        return instance;
    }

    public void addHandler(SerialDataHandler<String> handler) {
        if (handler != null && !handlers.contains(handler)) {
            handlers.add(handler);
        }
    }

    public void removeHandler(SerialDataHandler<String> handler) {
        handlers.remove(handler);
    }

    public boolean connect(String portName, int baudRate) {
        if (activePort != null && activePort.isOpen()) {
            return true;
        }

        activePort = SerialPort.getCommPort(portName);
        activePort.setBaudRate(baudRate);
        activePort.setComPortTimeouts(
                SerialPort.TIMEOUT_READ_SEMI_BLOCKING,
                1000,
                0
        );

        if (activePort.openPort()) {
            System.out.println("INFO: Port " + portName + " terbuka.");
            setupListener();
            return true;
        }

        System.err.println("ERROR: Gagal membuka port " + portName);
        return false;
    }

    private void setupListener() {
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }

                try {
                    byte[] buffer = new byte[activePort.bytesAvailable()];
                    int bytesRead = activePort.readBytes(buffer, buffer.length);

                    if (bytesRead > 0) {
                        String data = new String(buffer, 0, bytesRead).trim();

                        if (!data.isEmpty()) {
                            broadcast(data);
                        }
                    }

                } catch (Exception e) {
                    System.err.println("ERROR membaca serial: " + e.getMessage());
                }
            }
        });
    }

    private void broadcast(String data) {
        for (SerialDataHandler<String> handler : handlers) {
            handler.onDataReceived(data);
        }
    }

    public void disconnect() {
        if (activePort != null && activePort.isOpen()) {
            activePort.removeDataListener();
            activePort.closePort();
            System.out.println("INFO: Port ditutup.");
        }
    }

    public boolean isConnected() {
        return activePort != null && activePort.isOpen();
    }
}