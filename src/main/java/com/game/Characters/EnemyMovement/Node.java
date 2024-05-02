package com.game.Characters.EnemyMovement;

/**
 * Represents a single node in the grid for pathfinding purposes.
 * <p>
 * Each {@code Node} corresponds to a specific location in the game environment
 * grid and holds information relevant to the pathfinding algorithm, including
 * its position, pathfinding costs, and state. It is used by the {@link PathFinder}
 * to determine and track the shortest path between two points.
 *
 */
public class Node {
    Node parent;
    public int col,row;
    int gCost,hCost,fCost;
    boolean solid,open,checked;

    /**
     * Constructs a {@code Node} with specified grid coordinates.
     * <p>
     * Initializes a node with the given column and row indices. Pathfinding
     * costs and state flags are set to their default values.
     *
     * @param col the column index of this node in the grid
     * @param row the row index of this node in the grid
     */
    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
}
