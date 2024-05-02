package com.game.Characters.EnemyMovement;

import com.game.Characters.Character;
import com.game.GamePanel.MainGamePanel;

import java.util.ArrayList;

/**
 * Provides functionality to find paths for enemies in a game environment.
 * <p>
 * The {@code PathFinder} class uses a grid-based approach to determine
 * the shortest path for an enemy character from a starting point to a goal
 * point considering obstacles and terrain costs. This class relies on a
 * simplified A* (A-Star) pathfinding algorithm and is specifically tailored
 * for use within a {@link MainGamePanel} context.
 *
 */
public class PathFinder {
    MainGamePanel gamePanel;
    public Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    public boolean goalReached = false;
    int step = 0;

    public PathFinder(MainGamePanel gamePanel){
        this.gamePanel = gamePanel;
        instantiateNode();
    }

    /**
     * Instantiates grid nodes based on the dimensions provided by the {@link MainGamePanel}.
     * <p>
     * This method creates a grid of {@link Node} objects that represent possible
     * positions in the game environment. Each node is initialized with its grid
     * coordinates but without any pathfinding costs or state.
     */
    public void instantiateNode(){
        node = new Node[gamePanel.maxScreenCol][gamePanel.maxScreeRow];

        for (int r = 0; r < gamePanel.maxScreeRow; r++) {
            for (int c = 0; c < gamePanel.maxScreenCol; c++) {
                node[c][r] = new Node(c, r);
            }
        }
    }

    /**
     * Resets the pathfinding grid and state for a new pathfinding operation.
     * <p>
     * All nodes are reset to their initial state, clearing any previous pathfinding
     * data such as costs and parent nodes. Additionally, the open and path lists
     * are cleared, and pathfinding state variables are reinitialized.
     */
    public  void resetNodes(){

        for (int r = 0; r < gamePanel.maxScreeRow; r++) {
            for (int c = 0; c < gamePanel.maxScreenCol; c++) {
                node[c][r].open = false;
                node[c][r].checked = false;
                node[c][r].solid = false;
            }
        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;

    }

    /**
     * Prepares the pathfinding grid with starting and goal nodes and updates node states.
     * <p>
     * This method sets the starting and goal nodes based on provided coordinates
     * and updates the grid to reflect obstacles and terrain costs by analyzing
     * the game environment. It marks nodes as solid (impassable) if they correspond
     * to obstacles in the game environment.
     *
     * @param startCol the column index of the start node
     * @param startRow the row index of the start node
     * @param goalCol the column index of the goal node
     * @param goalRow the row index of the goal node
     * @param character the {@link Character} for which the path is being found
     */
    public void setNode(int startCol, int startRow, int goalCol, int goalRow, Character character) {
        resetNodes();

        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        for (int r = 0; r < gamePanel.maxScreeRow; r++) {
            for (int c = 0; c < gamePanel.maxScreenCol; c++) {
                int tileNum = gamePanel.tileM.getMapTileNum()[c][r];
//                if (gamePanel.tileM.getTile()[tileNum].getCollisionOn()) {
//                    node[c][r].solid = true;
//                    System.out.println("check");
//                }

                getCost(node[c][r]);
            }
        }
    }

    /**
     * Calculates and updates the pathfinding costs for a given node.
     * <p>
     * This method computes the G, H, and F costs of a node. The G cost represents
     * the distance from the start node, and the H cost represents the heuristic
     * estimated distance to the goal node. The F cost is the sum of G and H costs,
     * used to determine the node's priority in pathfinding.
     *
     * @param node the {@link Node} for which costs are calculated
     */
    public void getCost(Node node){

        int xDistance = Math.abs(node.col-startNode.col);
        int yDistance = Math.abs(node.row-startNode.row);
        node.gCost = xDistance + yDistance;

//        xDistance = Math.abs(node.col-startNode.col);
//        yDistance = Math.abs(node.row-startNode.row);
        xDistance = Math.abs(node.col-goalNode.col);
        yDistance = Math.abs(node.row-goalNode.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;

    }

    /**
     * Executes the pathfinding search to find the shortest path to the goal.
     * <p>
     * This method iterates through available nodes, expanding paths and assessing
     * costs to determine the most efficient route to the goal. It utilizes A* search
     * algorithm principles, considering both the traveled distance and an estimate
     * of the distance to the goal. The search terminates either when the goal is reached
     * or a specified step limit is exceeded.
     *
     * @return {@code true} if a path to the goal was found, {@code false} otherwise
     */
    public boolean search() {

        while (!goalReached && step < 8000) { ///temp original: 500
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);

            }
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);

            }
            if (row + 1 < gamePanel.maxScreeRow) {
                openNode(node[col][row + 1]);

            }
            if (col + 1 < gamePanel.maxScreenCol) {
                openNode(node[col + 1][row]);

            }

            //find best node
            int bestNodeIndex = 0;
            int bestNodeCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                //check if this node's f cost is better
                if (openList.get(i).fCost < bestNodeCost) {
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(i).fCost;
                }

                  //if f cost is equal, check the g cost
//                } else if (openList.get(i).fCost == bestNodeCost) {
//                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
//                        bestNodeIndex = i;
//                    }
//                }

            }
            //if there is no node in the openlist, end the loop
            if (openList.isEmpty()) {
                break;
            }

            //after the loop, openList[bestNodeIndex] is the next step => currentNode
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();

            }

            step++;

        }
        return goalReached;
    }

    /**
     * Traces back the path from the goal node to the start node.
     * <p>
     * After reaching the goal, this method retraces the steps taken to reach the
     * goal node from the start node. The path is reconstructed by following the parent
     * nodes from the goal to the start, effectively mapping the path taken.
     *
     */
    public void trackThePath(){
         Node current = goalNode;
         while(current != startNode){
             pathList.add(0,current);
             current = current.parent;
         }
    }

    /**
     * Marks a node as open if it is not already opened, checked, or solid.
     * <p>
     * This method adds a node to the open list if it is traversable (not solid),
     * has not been checked, and is not already in the open list. Each opened node's
     * parent is set to the current node to track the path's progression.
     *
     * @param node the {@link Node} to be opened
     */
    public void openNode(Node node){
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

}


