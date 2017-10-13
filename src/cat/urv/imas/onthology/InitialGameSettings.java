/**
 * IMAS base code for the practical work.
 * Copyright (C) 2014 DEIM - URV
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cat.urv.imas.onthology;

import cat.urv.imas.agent.AgentType;
import cat.urv.imas.map.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Initial game settings and automatic loading from file.
 *
 * Use the GenerateGameSettings to build the game.settings configuration file.
 */
@XmlRootElement(name = "InitialGameSettings")
public class InitialGameSettings extends GameSettings {

    /**
     * Path cell.
     */
    public static final int P = 0;
    /**
     * Digger cell.
     */
    public static final int DC = -1;
    /**
     * Prospector cell.
     */
    public static final int PC = -2;
    /**
     * Manufacturing center cell.
     */
    public static final int MCC = -3;
    /**
     * Field cell.
     */
    public static final int F = -4;

    /**
     * City initialMap. Each number is a cell. The type of each is expressed by a
     * constant (if a letter, see above), or a building (indicating the number
     * of people in that building).
     */
    private int[][] initialMap
            = {
                {F, F, F, MCC, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},
                {F, P, P, P, P, P, P, P, P, P, P, DC, P, P, P, P, P, P, P, F},
                {F, P, PC, P, P, P, P, DC, P, P, P, P, P, P, P, P, P, P, DC, F},
                {F, P, P, F, F, F, F, F, F, P, P, F, F, F, F, F, F, F, F, F},
                {F, P, P, F, F, F, F, F, MCC, P, P, F, F, F, F, F, F, F, F, F},
                {F, PC, P, F, F, P, P, P, P, P, P, F, F, P, P, P, P, P, P, F},
                {F, P, P, F, F, P, P, P, P, P, P, F, F, P, P, P, P, P, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F},
                {F, P, P, F, F, P, DC, F, F, P, P, F, F, P, P, F, F, P, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F},
                {F, P, P, F, F, P, P, F, F, P, PC, F, F, P, P, F, F, P, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, MCC, F, P, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F, F, DC, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F, F, DC, P, F},
                {F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F},
                {F, P, P, MCC, F, P, P, F, F, P, P, F, F, P, P, F, F, P, P, F},
                {F, P, PC, P, DC, P, P, F, F, P, P, P, P, P, P, F, F, P, P, F},
                {F, P, P, P, P, P, P, F, F, P, P, P, P, P, P, F, F, DC, P, F},
                {F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F},
            };


    public int[][] getInitialMap() {
        return initialMap;
    }

    @XmlElement(required = true)
    public void setInitialMap(int[][] initialMap) {
        this.initialMap = initialMap;
    }

    public static final GameSettings load(String filename) {
        if (filename == null) {
            filename = "game.settings";
        }
        try {
            // create JAXBContext which will be used to update writer
            JAXBContext context = JAXBContext.newInstance(InitialGameSettings.class);
            Unmarshaller u = context.createUnmarshaller();
            InitialGameSettings starter = (InitialGameSettings) u.unmarshal(new FileReader(filename));
            starter.initMap();
            return starter;
        } catch (Exception e) {
            System.err.println(filename);
            System.exit(-1);
        }
        return null;
    }

    /**
     * Initializes the cell map.
     * @throws Exception if some error occurs when adding agents.
     */
    private void initMap() throws Exception {
        int rows = this.initialMap.length;
        int cols = this.initialMap[0].length;
        map = new Cell[rows][cols];
        int manufacturingCenterIndex = 0;
        this.agentList = new HashMap();

        int cell;
        PathCell c;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cell = initialMap[row][col];
                switch (cell) {
                    case DC:
                        c = new PathCell(row, col);
                        c.addAgent(new DiggerInfoAgent(AgentType.DIGGER, this.getDiggersCapacity()));
                        map[row][col] = c;
                        addAgentToList(AgentType.DIGGER, c);
                        break;
                    case PC:
                        c = new PathCell(row, col);
                        c.addAgent(new InfoAgent(AgentType.PROSPECTOR));
                        map[row][col] = c;
                        addAgentToList(AgentType.PROSPECTOR, c);
                        break;
                    case P:
                        map[row][col] = new PathCell(row, col);
                        break;
                    case MCC:
                        if (manufacturingCenterIndex >= manufacturingCenterPrice.length) {
                            throw new Error(getClass().getCanonicalName() + " : More manufacturing centers in the map than given prices");
                        }
                        if (manufacturingCenterIndex >= manufacturingCenterMetalType.length) {
                            throw new Error(getClass().getCanonicalName() + " : More manufacturing centers in the map than given metal types");
                        }
                        map[row][col] = new ManufacturingCenterCell(row, col, manufacturingCenterPrice[manufacturingCenterIndex], manufacturingCenterMetalType[manufacturingCenterIndex]);
                        manufacturingCenterIndex++;
                        break;
                    case F:
                        // Only SystemAgent can access to the SettableFieldCell
                        map[row][col] = new SettableFieldCell(row, col);
                        break;
                    default:
                        throw new Error(getClass().getCanonicalName() + " : Unexpected type of content in the 2D map");
                }
            }
        }
        if (manufacturingCenterIndex != manufacturingCenterPrice.length) {
            throw new Error(getClass().getCanonicalName() + " : Less manufacturing centers in the map than given prices.");
        }
        if (manufacturingCenterIndex != manufacturingCenterMetalType.length) {
            throw new Error(getClass().getCanonicalName() + " : Less manufacturing centers in the map than given metal types.");
        }
    }

    /**
     * Ensure agent list is correctly updated.
     *
     * @param type agent type.
     * @param cell cell where appears the agent.
     */
    private void addAgentToList(AgentType type, Cell cell) {
        List<Cell> list = this.agentList.get(type);
        if (list == null) {
            list = new ArrayList();
            this.agentList.put(type, list);
        }
        list.add(cell);
    }
}
