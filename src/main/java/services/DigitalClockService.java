package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JLabel;

/**
 * Layanan Jam Digital yang memberikan fleksibilitas kontrol thread ke UI.
 */
public class DigitalClockService {

    private final JLabel targetLabel;
    private final String pattern;

    public DigitalClockService(JLabel targetLabel, String pattern) {
        this.targetLabel = targetLabel;
        this.pattern = pattern;
    }

    /**
     * Menyiapkan objek Thread tanpa langsung menjalankannya.
     * @return Objek Thread dalam fase 'New'.
     */
    public Thread getThread() {
        Runnable clockTask = () -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.of("id", "ID"));
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    LocalDateTime now = LocalDateTime.now();
                    String timeFormatted = now.format(formatter);

                    targetLabel.setText(timeFormatted);

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " dihentikan.");
            }
        };
        return new Thread(clockTask);
    }
}