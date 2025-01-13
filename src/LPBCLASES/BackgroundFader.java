package LPBCLASES;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;

public class BackgroundFader {
    private static final int FADE_DURATION = 300;
    private static final int FADE_STEPS = 10;
    private int step;

    public void fadeBackground(JButton button, Color startColor, Color endColor) {
        Timer timer = new Timer();
        step = 0;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (step >= FADE_STEPS) {
                    timer.cancel();
                    return;
                }

                float ratio = (float) step / FADE_STEPS;
                int red = (int) (startColor.getRed() + ratio * (endColor.getRed() - startColor.getRed()));
                int green = (int) (startColor.getGreen() + ratio * (endColor.getGreen() - startColor.getGreen()));
                int blue = (int) (startColor.getBlue() + ratio * (endColor.getBlue() - startColor.getBlue()));

                button.setBackground(new Color(red, green, blue));
                step++;
            }
        };

        timer.scheduleAtFixedRate(task, 0, FADE_DURATION / FADE_STEPS);
    }
}