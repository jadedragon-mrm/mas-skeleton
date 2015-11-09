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
package cat.urv.imas.map;

import cat.urv.imas.agent.AgentType;
import cat.urv.imas.gui.CellVisualizer;
import cat.urv.imas.onthology.InfoAgent;
import jade.core.AID;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class keeps information about a street cell in the map.
 */
public class PathCell extends Cell {

    /**
     * Information about the agent the cell contains.
     */
    private Map<AgentType, Map<AID,InfoAgent>> agents = new HashMap();
    /**
     * Number of agents in this cell.
     */
    private int numberOfAgents = 0;
    /**
     * Injures people list.
     */
    private InjuredPeople injuredPeople = new InjuredPeople();
    /**
     * If true, this path has an avalanche. false means that the path is
     * free and available.
     */
    private boolean avalanche = false;

    /**
     * Builds a cell with a given type.
     *
     * @param row row number.
     * @param col column number.
     */
    public PathCell(int row, int col) {
        super(CellType.PATH, row, col);
    }

    public void addInjuredPeople(int amount, int stepToDie) {
        injuredPeople.addInjuredPeople(amount, stepToDie);
    }

    public InjuredPeople getInjuredPeople() {
        return injuredPeople;
    }

    /**
     * Is this path cell covered by an avalanche?
     * @return has an avalanche.
     */
    public boolean isAvalanche() {
        return avalanche;
    }

    /**
     * Sets or unsets whether this cell is an avalanche.
     * @param avalanche true to contain an avalanche. false otherwise.
     */
    public void setAvalanche(boolean avalanche) {
        this.avalanche = avalanche;
    }

    /* ***************** Map visualization API ********************************/
    @Override
    public void draw(CellVisualizer visual) {
        if (isAvalanche()) {
            visual.drawAvalanche(this);
        } else {
            visual.drawPath(this);
        }
    }
}
