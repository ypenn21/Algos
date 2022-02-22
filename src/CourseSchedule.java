import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;

public class CourseSchedule {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue < Integer > queue = new PriorityQueue< >((a, b) -> b - a);
//        PriorityQueue < Integer > queue2 = new PriorityQueue< >((a, b) -> {return b - a;});
        int time = 0;
        for (int[] c: courses) {
            if (time + c[0] <= c[1]) {
                queue.offer(c[0]);
                time += c[0];
            } else if (!queue.isEmpty() && queue.peek() > c[0]) {
                time += c[0] - queue.poll();
                queue.offer(c[0]);
            }
        }
        return queue.size();
    }

    public static void main(String... args) {
        CourseSchedule schedule = new CourseSchedule();
        int[][] courses = {
                {100, 200},
                {200, 1300},
                {1000, 1250},
                {2000, 3200}
        };
        System.out.println(schedule.scheduleCourse(courses));
        Integer[] courseTimes = {100, 200, 1000, 2000};
        Map<Integer, Integer> courseTimeNCount = new HashMap<>();
        IntStream.range(0, courseTimes.length).forEach(index ->{
            courseTimeNCount.put( courseTimes[index], 1);
        });
//        List myList = new ArrayList();
//        schedule.findAllCombination(courseTimes, courseTimeNCount, 0, 0, myList);
//        System.out.println(myList.toString());
//        System.out.println("solution 2");
//        List r = new ArrayList<>();
//        schedule.combinationEasy(courseTimes, 0, r);
//        System.out.println(r);
    }

    public class CourseTimeNCount {
        public final Integer time;
        public final Integer count;
        public CourseTimeNCount(Integer time, Integer count) {
            this.time = time;
            this.count = count;
        }
    }

    private void combinationEasy(Integer[] courses, int pos, List r) {

        r.forEach(r1 -> System.out.print(r1 + " "));
        System.out.println();
        for (int i = pos; i < courses.length; i++) {
//            if (i != pos && courses[i] == courses[i-1]) {
//                continue;
//            }
            r.add(courses[i]);
            combinationEasy(courses, i + 1, r);
            r.remove(r.size() - 1);
        }
    }

    public void findAllCombination(Integer[] courses, Map<Integer, Integer> courseTimeNCount, int position, int leng, List combos) {
        List combo = new ArrayList();
        IntStream.range(position, leng).forEach(index -> {
            combo.add(courses[index]);
        });
        if(!combo.isEmpty())
            combos.add(combo);
        for(int i =0;i<courses.length;i++){
            Integer countTime = courseTimeNCount.get(courses[i]);
            if(countTime==0) {
                continue;
            }
            //            output[len] = input[i];
            courseTimeNCount.put(courses[i], countTime-1);

            findAllCombination(courses, courseTimeNCount, i, leng+1, combos);
            courseTimeNCount.put(courses[i], countTime++);
        }
    }
}