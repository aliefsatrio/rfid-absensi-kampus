package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class DigitalClockService {

    private final JLabel lblTanggal;
    private final JLabel lblJam;

    public DigitalClockService(JLabel lblTanggal,
                               JLabel lblJam) {

        this.lblTanggal = lblTanggal;
        this.lblJam = lblJam;

    }

    public Thread getThread() {

        Runnable task = () -> {

            DateTimeFormatter tanggalFormatter =
                    DateTimeFormatter.ofPattern(
                            "EEEE, dd MMMM yyyy",
                            new Locale("id", "ID")
                    );

            DateTimeFormatter jamFormatter =
                    DateTimeFormatter.ofPattern(
                            "HH:mm:ss"
                    );

            try {

                while (!Thread.currentThread().isInterrupted()) {

                    LocalDateTime now = LocalDateTime.now();

                    SwingUtilities.invokeLater(() -> {

                        lblTanggal.setText(
                                now.format(tanggalFormatter)
                        );

                        lblJam.setText(
                                now.format(jamFormatter)
                        );

                    });

                    Thread.sleep(1000);

                }

            } catch (InterruptedException ex) {

                System.out.println("Clock dihentikan.");

            }

        };

        return new Thread(task);

    }

}