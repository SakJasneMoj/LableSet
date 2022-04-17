package sk.uniza.fri;

import java.util.ArrayList;

public class LabelSet {
    private int verticesCount;
    private int[][] edges;

    public LabelSet(int verticesCount, int[][] edges) {
        this.verticesCount = verticesCount;
        this.edges = edges;
    }

    public int[] getShortestPath(int from, int to, LabelAlgorithmForm labelAlgorithmForm) {
        if (from < 1 || to < 1 || from > verticesCount || to > verticesCount) return null;

        double[] shortestPathTo = new double[verticesCount + 1];
        for (int i = 1; i < shortestPathTo.length; i++) shortestPathTo[i] = Double.POSITIVE_INFINITY;
        shortestPathTo[from] = 0;

        int[] predecessors = new int[verticesCount + 1];
        for (int i = 1; i < predecessors.length; i++) predecessors[i] = 0;

        ArrayList<Integer> epsilon = new ArrayList<>();
        epsilon.add(from);

        while (epsilon.size() > 0) {
            int currentVertex;
            if (labelAlgorithmForm == LabelAlgorithmForm.LABEL_CORRECT)
                currentVertex = epsilon.get(0);
            else
                currentVertex = getLabelSetNewVertex(shortestPathTo, epsilon);

            epsilon.remove((Integer) currentVertex);

            for (int[] edge : edges) {
                if (edge[0] == currentVertex) {
                    double newShortestPath = shortestPathTo[currentVertex] + edge[2];
                    if (newShortestPath < shortestPathTo[edge[1]]) {
                        shortestPathTo[edge[1]] = newShortestPath;
                        predecessors[edge[1]] = currentVertex;
                        epsilon.add(edge[1]);
                    }
                }
            }
        }

        if (shortestPathTo[to] == Double.POSITIVE_INFINITY) return null;

        ArrayList<Integer> result = new ArrayList<>();
        int current = to;
        while (current != from) {
            result.add(current);
            current = predecessors[current];
        }
        result.add(from);

        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++)
            resultArray[result.size() - i - 1] = result.get(i);

        return resultArray;
    }

    public int getLabelSetNewVertex(double[] shortestPathTo, ArrayList<Integer> epsilon) {
        double minFound = Double.POSITIVE_INFINITY;
        int result = -1;
        for (Integer integer : epsilon) {
            if (shortestPathTo[integer] < minFound) {
                minFound = shortestPathTo[integer];
                result = integer;
            }
        }
        return result;
    }

    public enum LabelAlgorithmForm {
        LABEL_CORRECT,
        LABEL_SET
    }
}
