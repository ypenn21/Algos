/**
 * Created by yannipeng on 4/20/18.
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

import java.util.*;



public class MergeIntervals {

    public static void main(String[] args){
        MergeIntervals mergeIntervals = new MergeIntervals();
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        intervals.add( new Interval(1, 4) );
        intervals.add( new Interval(4, 10) );
        intervals.add( new Interval(1, 11) );
        intervals.add( new Interval(14, 16) );
        intervals.add( new Interval(20, 21) );
        Set set = mergeIntervals.mergeSlowWay(intervals);
        set.stream().forEach(item->{ System.out.println(item.toString()); });
        System.out.println("Done");
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        public String toString(){
            return start+"-"+end;
        }

        public Boolean equals(Interval a, Interval b){
            return a.start  == b.start && a.end == b.end;
        }
    }

    public Set<String> mergeSlowWay(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(intervals.size() == 0)
            return new HashSet<>();
        if(intervals.size() == 1)
            return new HashSet<>();

        //Collections.sort(intervals, new IntervalComparator());

//        Interval first = intervals.get(0);
//        int start = first.start;
//        int end = first.end;

        Set<String> result = new HashSet<String>();
        ArrayList<Integer> addIndex = new ArrayList<Integer>();
        for(int i = 0; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            Boolean merged = false;
            //Set<Interval> resultNested = new HashSet<Interval>();
            int start = current.start;
            int end = current.end;
            for (int nestedInd = 0; nestedInd < intervals.size(); nestedInd++) {
                Interval currentNest = intervals.get(nestedInd);
                if (currentNest.start <= end) {
                    if(currentNest.end>=end){
                        merged = true;
                        end = Math.max(currentNest.end, end);
                        start = Math.min(currentNest.start, start);
                    }
                }
            }

            if(merged){
                Interval addInterval = new  Interval(start, end);
                if(!result.contains(addInterval.toString())) {
                    result.add(addInterval.toString());
                }
            }
            else {
                addIndex.add(i);
            }
        }

        return result;

    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(intervals.size() == 0)
            return intervals;
        if(intervals.size() == 1)
            return intervals;

        Collections.sort(intervals, new IntervalComparator());

        Interval first = intervals.get(0);
        int start = first.start;
        int end = first.end;

        ArrayList<Interval> result = new ArrayList<Interval>();

        for(int i = 1; i < intervals.size(); i++){
            Interval current = intervals.get(i);
            if(current.start <= end){
                end = Math.max(current.end, end);
            }else{
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }

        }

        result.add(new Interval(start, end));

        return result;

    }

    class IntervalComparator implements Comparator{
        public int compare(Object o1, Object o2){
            Interval i1 = (Interval)o1;
            Interval i2 = (Interval)o2;
            return i1.start - i2.start;
        }
    }
}