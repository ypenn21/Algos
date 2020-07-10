import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AirBNBWizards {
    public static void main(String[] arguments) {
        List wizards = Arrays.asList(new String[] {
                "3 2 1",
                "8 6 4",
                "7 5 3",
                "8 1",
                "6",
                "8 7",
                "5 4 9",
                "4 6",
                "1",
                "1 4"
        });
        int[] shortestPathToTargetWizard = meet(wizards);
        if(shortestPathToTargetWizard[0]==wizards.size()-1){
            // Should return {0, 1, 4, 6, 9}.
            System.out.println("-1");
        } else {
            printIntArray(shortestPathToTargetWizard);
        }
    }

    private static int[] constructPathToTarget(List<Integer> wizardPreviousConnections, int startingWizard, int targetWizard) {
        int currentWizard = targetWizard;
        LinkedList<Integer> pathToTarget = new LinkedList<>();
        do {
            pathToTarget.addFirst(currentWizard);
            currentWizard = wizardPreviousConnections.get(currentWizard);
        } while(currentWizard != -1 && currentWizard!=targetWizard);
        return pathToTarget.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int[] meet(List<String> wizards) {
        if (null == wizards) {
            throw new InvalidParameterException();
        }
        int[] pathToTargetWizard = null;
        List<List<Integer>> wizardConnections = wizards.stream().map(wizardConnectionsString -> {
            return Arrays.asList(wizardConnectionsString.split("\\s+")).stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        })
        .collect(Collectors.toList());
        pathToTargetWizard = findPathToTargetWizard2(wizardConnections, 0, wizardConnections.size() - 1);

        return pathToTargetWizard;
    }

    public void test(){

    }

    private static int[] findPathToTargetWizard(List<List<Integer>> wizardConnections, int startingWizard, int targetWizard) {
        // Use Dikstra's algorithm to find the shortest path to the target wizard.
        int currentWizard = startingWizard;
        int currentWizardMinDistance = 0;
        Set<Integer> visitedWizards = new HashSet<>();
        List<Integer> nextWizardsToBeVisited = new ArrayList<>();
        List<Integer> wizardConnectionMinimumDistances = new ArrayList<>();
        List<Integer> wizardPreviousWizardConnections = new ArrayList<>();
        nextWizardsToBeVisited.add(currentWizard);
        IntStream.range(0, wizardConnections.size())
                .forEach(i -> {
                    wizardConnectionMinimumDistances.add(Integer.MAX_VALUE);
                    wizardPreviousWizardConnections.add(i);
                });
        wizardConnectionMinimumDistances.set(startingWizard, 0);
        wizardPreviousWizardConnections.set(startingWizard, -1);
        int wizardConnection = -1;
        currentWizard = getWizardWithLowestMinDistanceNotVisited(wizardConnectionMinimumDistances, visitedWizards, nextWizardsToBeVisited);
        boolean foundTarget = false;
        while (currentWizard != targetWizard && currentWizard != -1 && visitedWizards.size() < wizardConnections.size()) {
            visitedWizards.add(currentWizard);
            currentWizardMinDistance = wizardConnectionMinimumDistances.get(currentWizard);
            List<Integer> currentWizardConnections = wizardConnections.get(currentWizard);
            for (int i = 0; i < currentWizardConnections.size(); i++) {
                wizardConnection = currentWizardConnections.get(i);
                int currentWizardConnectionMinDistance = wizardConnectionMinimumDistances.get(wizardConnection);
                int currentWizardMinDistancePlusDistanceToWizardConnection = currentWizardMinDistance + 1; // All wizards have a connection edge weight of 1.
                if (currentWizardMinDistancePlusDistanceToWizardConnection < currentWizardConnectionMinDistance) {
                    wizardConnectionMinimumDistances.set(wizardConnection, currentWizardMinDistancePlusDistanceToWizardConnection);
                    wizardPreviousWizardConnections.set(wizardConnection, currentWizard);
                }
                nextWizardsToBeVisited.add(wizardConnection);
            }
            currentWizard = getWizardWithLowestMinDistanceNotVisited(wizardConnectionMinimumDistances, visitedWizards, nextWizardsToBeVisited);
            if(currentWizard == targetWizard) {
                foundTarget = true;
            }
        }
        return constructPathToTarget(wizardPreviousWizardConnections, startingWizard, targetWizard);
    }

    public static int getPriority(Integer code){

        return code;
    }

    @SuppressWarnings("Duplicates")
    private static int[] findPathToTargetWizard2(List<List<Integer>> wizardConnections, int startingWizard, int targetWizard) {
        // Use Dikstra's algorithm to find the shortest path to the target wizard.
        int currentWizard2Crawler = startingWizard;
        int currentWizard2MinDistance = 0;
        Set<Integer> visitedWizards = new HashSet<>();
        List<Integer> wizardConnectionMinimumDistances = new ArrayList<>();
        List<Integer> path2TargetWizard = new ArrayList<>();
        PriorityQueue< Integer > queue = new PriorityQueue< >((a, b) ->
            Integer.compare(a, b));
        queue.add(currentWizard2Crawler);
        IntStream.range(0, wizardConnections.size())
                .forEach(i -> {
                    wizardConnectionMinimumDistances.add(Integer.MAX_VALUE);
                    path2TargetWizard.add(i);
                });
        wizardConnectionMinimumDistances.set(startingWizard, 0);
        path2TargetWizard.set(startingWizard, -1);
        int wizardConnection = -1;
        currentWizard2Crawler = getWizardWithLowestMinDistanceNotVisited(wizardConnectionMinimumDistances, visitedWizards, queue);
        boolean foundTarget = false;
        while (currentWizard2Crawler != targetWizard && currentWizard2Crawler != -1 && visitedWizards.size() < wizardConnections.size()) {
            visitedWizards.add(currentWizard2Crawler);
            currentWizard2MinDistance = wizardConnectionMinimumDistances.get(currentWizard2Crawler);
            List<Integer> currentWizardConnections = wizardConnections.get(currentWizard2Crawler);
            for (int i = 0; i < currentWizardConnections.size(); i++) { //looping nested array
                wizardConnection = currentWizardConnections.get(i);
                int currentWizard2MinDistancePlusDistanceToWizardConnection = currentWizard2MinDistance + 1; // All wizards have a connection edge weight of 1.
                if (currentWizard2MinDistancePlusDistanceToWizardConnection < wizardConnectionMinimumDistances.get(wizardConnection)) {
                    wizardConnectionMinimumDistances.set(wizardConnection, currentWizard2MinDistancePlusDistanceToWizardConnection);
                    path2TargetWizard.set(wizardConnection, currentWizard2Crawler);
                }
                // adding to the queue but add only if the crawl wizard is less than the current wizard we are checking
                if(currentWizard2Crawler<wizardConnection)
                    queue.add(wizardConnection);
            } // end of looping nested array
            currentWizard2Crawler = getWizardWithLowestMinDistanceNotVisited(wizardConnectionMinimumDistances, visitedWizards, queue);
            if(currentWizard2Crawler == targetWizard) {
                foundTarget = true;
            }
        }
        return constructPathToTarget(path2TargetWizard, startingWizard, targetWizard);
    }

    private static int getWizardWithLowestMinDistanceNotVisited(List<Integer> wizardMinimumDistances, Set<Integer> visitedWizards, PriorityQueue< Integer > queue) {
        int currentWizardToBeVisited = -1;
        while (!queue.isEmpty() && currentWizardToBeVisited==-1) {
            Integer nextWizard = queue.poll();
            if (!visitedWizards.contains(nextWizard)
                    && (wizardMinimumDistances.get(nextWizard) < Integer.MAX_VALUE)) { // || wizardMinimumDistances.get(nextWizard) == Integer.MAX_VALUE
                currentWizardToBeVisited = nextWizard;
            }
        }
        return currentWizardToBeVisited;
    }

    private static int getWizardWithLowestMinDistanceNotVisited(List<Integer> wizardMinimumDistances, Set<Integer> visitedWizards, List<Integer> nextWizardsToBeVisited) {
        int wizardWithLowestMinDistanceNotVisited = -1;
        int indexOfWizardWithLowestMinDistanceNotVisited = -2;
        int minDistanceFound = Integer.MAX_VALUE;
        int currentWizardToBeVisited;
        for (int i = 0; i < nextWizardsToBeVisited.size(); i++) {
            currentWizardToBeVisited = nextWizardsToBeVisited.get(i);
            if ( !visitedWizards.contains(currentWizardToBeVisited)
                    && (wizardMinimumDistances.get(currentWizardToBeVisited) < minDistanceFound
                        || wizardMinimumDistances.get(currentWizardToBeVisited) == minDistanceFound && currentWizardToBeVisited < wizardWithLowestMinDistanceNotVisited) ) {
                wizardWithLowestMinDistanceNotVisited = currentWizardToBeVisited;
                indexOfWizardWithLowestMinDistanceNotVisited = i;
            }
        }
        if(indexOfWizardWithLowestMinDistanceNotVisited>=0)
            nextWizardsToBeVisited.remove(indexOfWizardWithLowestMinDistanceNotVisited);
        return wizardWithLowestMinDistanceNotVisited;
    }

    private static void printIntArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}