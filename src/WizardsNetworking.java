import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class WizardsNetworking {

    public static void main(String[] arguments) {
        List wizards = Arrays.asList(new String[] {
                "1 2 3",
                "8 6 4",
                "7 8 3",
                "8 1",
                "6",
                "8 7",
                "9 4",
                "4 6",
                "1",
                "1 4"
        });
        List<Integer> shortestPathToTargetWizard = meet(wizards); // Should return {0, 1, 4, 6, 9}.
//        printIntArray(shortestPathToTargetWizard);
    }

    private static List<Integer> meet(List wizards) {
        //all the data structures needed
        List<Integer> pathToN = new ArrayList();
        List<Integer> shortestDistance = new ArrayList<>();
        Set<Integer> wizardsVisted = new HashSet();
        List<Integer> toBeProcessed = new ArrayList<>();
        IntStream.range(0, wizards.size()).forEach(i ->{
                shortestDistance.add(Integer.MAX_VALUE);
                pathToN.add(Integer.MAX_VALUE);
            }
        );
        //set the starting point
        shortestDistance.set(0,0);
        pathToN.set(0,0);
        toBeProcessed.add(0);
        Integer currentWiz = findMinDistanceWizardToProcess(toBeProcessed, shortestDistance, wizardsVisted);
        int index = 0;
        while(currentWiz != wizards.size()-1 && wizardsVisted.size()<wizards.size()) {
            wizardsVisted.add(currentWiz);
            String wizard = (String) wizards.get(currentWiz);
            String[] wizardNetwork = wizard.split(" ");
            for(String wizardConnection : wizardNetwork){
                toBeProcessed.add(Integer.parseInt(wizardConnection));
            }

            for(int toProcess : toBeProcessed) {
                int distanceBetweenWizards = shortestDistance.get(currentWiz)+1;
                if(shortestDistance.get(toProcess) >  distanceBetweenWizards){
                    shortestDistance.set(toProcess, distanceBetweenWizards);
                    pathToN.set(toProcess, currentWiz);
                }

            }
            currentWiz = findMinDistanceWizardToProcess(toBeProcessed, shortestDistance, wizardsVisted);
        }

        return pathToN;
    }

    // remove toBeProcessed as each int gets processed.
    private static int findMinDistanceWizardToProcess(List<Integer> toBeProcessed, List<Integer> shortestDistance, Set<Integer> wizardsVisted){
        Integer minDisWiz =-1;

        for(int i=0;i<toBeProcessed.size();i++){
            Integer currentWizard = toBeProcessed.get(i);
            if(!wizardsVisted.contains(currentWizard)
                    && shortestDistance.get(currentWizard) < Integer.MAX_VALUE ){
                minDisWiz = toBeProcessed.get(i);
            }
        }
        toBeProcessed.remove(minDisWiz);
        return minDisWiz;

    }
}
