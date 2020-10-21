package yanni.com.unit2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerMoving {

    public List evenDistrbuteRegions(List<List<Integer>> servers, int regions) {
        int serversSize = servers.size();
        int even = regions/serversSize;

        List moreThanEven = new ArrayList();
        List lessThanEven = new ArrayList();

        Collections.sort(servers, new Comparator<List>() {
            @Override
            public int compare(List o1, List o2) {
                if(o1.size()>o2.size()) {
                    return 1;
                } else if (o1.size()<o2.size()){
                    return -1;
                }
                return 0;
            }
        });

        List evenServers = servers.stream().filter(integers -> integers.size()==even).collect(Collectors.toList());

        int lessEvenStart=0;
        for (List server:servers) {
            if(server.size()<even){
                lessThanEven.add(server);
            }
            if(server.size()==even){
                break;
            }
            lessEvenStart++;
        }

        for (int i = servers.size()-1;i>lessEvenStart;i--) {
            if(servers.get(i).size()>even) {
                moreThanEven.add(servers.get(i));
            }
        }

        List result = redistrbuteRegions(moreThanEven, lessThanEven, even);
        result.addAll(evenServers);
        return result;
    }


    private List redistrbuteRegions(List<List<Integer>> moreThanEvens, List<List<Integer>> lessThanEvens, int even) {

        List result = new LinkedList<>();

        for (List<Integer> lessThanEven : lessThanEvens){
            for (List<Integer> moreThanEven  : moreThanEvens){
                if(moreThanEven.size()==even){
                    continue;
                }
                if (lessThanEven.size()==even) {
                    break;
                } else {
                    int i=0;
                    Iterator<Integer> iter = moreThanEven.iterator();
                    while (iter.hasNext()) {
                        if(lessThanEven.size()==even || moreThanEven.size()==even){
                            break;
                        }
                        lessThanEven.add(iter.next());
                        iter.remove();
                        i++;
                    }
                }
            }

        }

        result.addAll(lessThanEvens);
        result.addAll(moreThanEvens);

        return result;

    }


    public static void main(String args[]) {
        int[][] array = {{1, 1, -2 ,3},
                         {1, 1},
                         {1, 1, -2},
                         {1, 1, -2,3,3},
                         {1}
//                         {},
//                         {1, 1, 1,1,1},
//                         {1, 1, 1,1}
                        };
        List<List<Integer>> myArray= new LinkedList<>();
        for(int i=0; i<array.length;i++) {
            System.out.println(array[i]);
            List<Integer> myList = new ArrayList();
            for(int num : array[i]){
                myList.add(num);
            }
            myArray.add(myList);
        }

        ServerMoving serverMoving = new ServerMoving();
        List resultservers = serverMoving.evenDistrbuteRegions(myArray, 15);
        System.out.println(resultservers);
    }



}