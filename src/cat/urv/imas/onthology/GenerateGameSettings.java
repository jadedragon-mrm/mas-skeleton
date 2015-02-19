/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.imas.onthology;

import static cat.urv.imas.onthology.InitialGameSettings.A;
import static cat.urv.imas.onthology.InitialGameSettings.F;
import static cat.urv.imas.onthology.InitialGameSettings.H;
import static cat.urv.imas.onthology.InitialGameSettings.P;
import static cat.urv.imas.onthology.InitialGameSettings.S;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 * Helper for updating the game settings. To do so, just update the content of
 * the <code>defineSettings()</code> method.
 */
public class GenerateGameSettings {

    private static final int STEPS = 600;
    private static final String FILENAME = "game.evaluation.seconddate.settings";
    private static final int MAX_CITIZENS = 10;

    /*
     * ********************* JUST SET YOUR SETTINGS ****************************
     */
    /**
     * Override the default settings to what you need.
     *
     * @param settings GameSettings instance.
     */
    public static void defineSettings(InitialGameSettings settings) {
        settings.setSeed(1234567.8f);
        settings.setHospitalCapacities(new int[]{5, 5, 5});
        settings.setSimulationSteps(STEPS);
        settings.setTitle("Practical IMAS");
        //add here whatever settings.set* to define your new settings.
        // settings for first date
        int[][] map
            = {
                {10, 10, H, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                {10,  S,  S,  S,  P,  S,  S,  S,  S,  S,  S,  P,  S,  S,  S,  S,  S,  S,  A,  F,  S,  S,  S,  S, 10},
                {10,  S,  A,  S,  S,  S,  S,  F,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                {10,  S,  S, 10, 10,  S,  S,  S,  S,  S,  S, 10, 10,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S,  S,  S,  S,  S, 10, 10,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10, 10,  S,  S, 10},
                {10,  S,  S, 10,  H,  S,  A, 10, 10,  S,  S, 10, 10,  A,  S, 10, 10,  S,  S, 10, 10,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S,  H, 10,  S,  S, 10, 10,  S,  A, 10, 10,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  F,  S,  S, 10},
                {10,  S,  S, 10, 10,  F,  S, 10, 10,  S,  S, 10, 10,  P,  S, 10, 10,  S,  S, 10, 10,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  F,  S, 10, 10,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S,  S, 10},
                {10,  S,  S, 10, 10,  S,  S, 10, 10,  F,  S, 10, 10,  S,  S, 10, 10,  S,  S, H, 10,  S,  S,  P, 10},
                {10,  S,  A,  S,  S,  S,  S, 10, 10,  S,  S,  S,  S,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S,  S, 10},
                {10,  S,  S,  S,  S,  S,  S, 10, 10,  S,  S,  S,  S,  S,  S, 10, 10,  S,  S, 10, 10,  S,  S,  S, 10},
                {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},};
        
        for (int i=0; i < map.length; i++) {
            for (int j=0; j < map[i].length; j++) {
                if (map[i][j] != 10) {
                    continue;
                }
                Random random = new Random();
                map[i][j] = 5 + random.nextInt(MAX_CITIZENS);
            }
        }
        settings.setInitialMap(map);
        settings.setSeed(136789.1f);
        settings.setHospitalCapacities(new int[]{20, 20, 20, 40});
        settings.setSimulationSteps(STEPS);
        settings.setTitle("IMAS: Evaluation of practica work. Second date ;-)");
        
    }

    /*
     * ********************* DO NOT MODIFY BELOW *******************************
     */
    /**
     * Produces a new settings file to be loaded into the game.
     *
     * @param args nothing expected.
     */
    public static final void main(String[] args) {
        InitialGameSettings settings = new InitialGameSettings();

        defineSettings(settings);
        storeSettings(settings);
        testSettings();
    }

    /**
     * Produces an XML file with the whole set of settings from the given
     * GameSettings.
     *
     * @param settings GameSettings to store in a file.
     */
    private static void storeSettings(InitialGameSettings settings) {
        try {

            //create JAXBElement of type GameSettings
            //Pass it the GameSettings object
            JAXBElement<InitialGameSettings> jaxbElement = new JAXBElement(
                    new QName(InitialGameSettings.class.getSimpleName()), InitialGameSettings.class, settings);

            //Create a String writer object which will be 
            //used to write jaxbElment XML to string
            StringWriter writer = new StringWriter();

            // create JAXBContext which will be used to update writer 		
            JAXBContext context = JAXBContext.newInstance(InitialGameSettings.class);

            // marshall or convert jaxbElement containing GameSettings to xml format
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            marshaller.marshal(jaxbElement, writer);

            //print XML string representation of GameSettings
            try {
                PrintWriter out = new PrintWriter(FILENAME, "UTF-8");
                out.println(writer.toString());
                out.close();
            } catch (Exception e) {
                System.err.println("Could not create file '" + FILENAME + "'.");
                System.out.println(writer.toString());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that settings file was created and it is readable again.
     */
    private static void testSettings() {
        try {
            GameSettings settings = InitialGameSettings.load(FILENAME);
            if (settings.getSimulationSteps() != STEPS) {
                throw new Exception("Something went wrong, we loaded some different to what we stored.");
            }
            System.out.println("Settings loaded again. Ok!");
        } catch (Exception e) {
            System.err.println("Settings could not be loaded!");
            e.printStackTrace();
        }
    }

}
