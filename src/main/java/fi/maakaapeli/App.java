package fi.maakaapeli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.maakaapeli.day1.SonarSweep;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        day1();
    }

    public static void day1() {
        SonarSweep sonarSweep = new SonarSweep();
        log.info("Larger count with singles: " + sonarSweep.getLargerCount());
        log.info("Larger count with three-measurement-window: " + sonarSweep.getLargerCountThreeMeasurementWindow());
    }
}
