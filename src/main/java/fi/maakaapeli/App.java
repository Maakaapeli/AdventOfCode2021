package fi.maakaapeli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.maakaapeli.day1.SonarSweep;
import fi.maakaapeli.day2.Dive;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        System.out.println("----------------------");
        day1();
        System.out.println("----------------------");
        day2();
        System.out.println("----------------------");
    }

    public static void day1() {
        SonarSweep sonarSweep = new SonarSweep();
        log.info("Day 1");
        log.info("Larger count with singles: " + sonarSweep.getLargerCount());
        log.info("Larger count with three-measurement-window: " + sonarSweep.getLargerCountThreeMeasurementWindow());
    }

    public static void day2() {
        Dive dive = new Dive();
        log.info("Day 2");
        log.info("Final depth for our dive is: " + dive.getFinalDepth());
        log.info("Final depth with aim for our dive is: " + dive.getFinalDepthWithAim());
    }
}
