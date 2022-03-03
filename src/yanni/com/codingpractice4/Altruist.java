package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Altruist {
    public static final int NAME = 0;
    public static final int ACTION = 1;

    public List<List<String>> security(String [][] input) {
        Map<String, String> noExit = new LinkedHashMap<>();
        Map<String, String> noEnter = new LinkedHashMap<> ();

        for(int i=0; i<input.length; i++) {
            String[] br = input[i];
            if (br[ACTION].equals("enter")) {
                if(noExit.get(br[NAME]) !=null) {
                    noExit.put(br[NAME], br[ACTION]);
                }
                noExit.put(br[NAME], br[ACTION]);
            } else if (br[ACTION].equals("exit")) {
                if(noExit.get(br[NAME]) !=null && noExit.get(br[NAME]).equals("enter")) {
                    noExit.remove(br[NAME]);
                } else {
                    noEnter.put(br[NAME], br[ACTION]);
                }
            }
        }

        List<List<String>> result = new ArrayList();
        result.add(new ArrayList(noExit.keySet()));
        result.add(new ArrayList(noEnter.keySet()));

        return result;
    }

    public List<List<String>> securityFinal(String [][] input) {
        Set<String> noExit = new HashSet<>();
        Set<String> noEnter = new HashSet<>();
        Set<String> inRoom = new HashSet<>();

        for(int i=0; i<input.length; i++) {
            String[] br = input[i];
            if (br[ACTION].equals("enter")) {
                if(inRoom.contains(br[NAME])) {
                    noExit.add(br[NAME]);
                    continue;
                }
                inRoom.add(br[NAME]);
            } else if (br[ACTION].equals("exit")) {
                if(!inRoom.contains(br[NAME])) {
                    noEnter.add(br[NAME]);
                }
                inRoom.remove(br[NAME]);
            }
        }

        noExit.addAll(inRoom);
        List<List<String>> result = new ArrayList();
        result.add(new ArrayList(noExit));
        result.add(new ArrayList(noEnter));
        System.out.print("no exit: " + noExit);
        System.out.println(" no entry: " + noEnter);
        System.out.println("-------------------------");
        return result;
    }





    public List<List<String>> security2(String [][] input) {
        Stack<String> noExit = new Stack<>();
        Stack<String> noEnter = new Stack<> ();

        for(int i=0; i<input.length; i++) {
            String[] br = input[i];
            if (br[ACTION].equals("enter")) {
                noExit.add(br[NAME]);
            } else if (br[ACTION].equals("exit")) {
                if(!noExit.isEmpty()) {
                    noExit.pop();
                } else {
                    noEnter.add(br[NAME]);
                }
            }
        }

//        for(int i=0; i<input.length; i++) {
//            String[] br = input[i];
//            if (br[ACTION].equals("exit")) {
//                noExit.pop();
//            } else if (br[NAME].equals("enter")) {
//                noEnter.pop();
//            }
//        }

        List<List<String>> result = new ArrayList<>();


        return null;
    }

    static void security3(String[][] input) {
        Map<String, StringBuilder> map = new HashMap<>();


        for (String[] record : input) {
            if (map.containsKey(record[0])) {
                if (record[1].equals("exit")) {
                    map.put(record[0], map.get(record[0]).append("1"));
                } else {
                    //assuming its always exit and entry
                    map.put(record[0], map.get(record[0]).append("0"));
                }
            } else {
                if (record[1].equals("exit")) {
                    map.put(record[0], new StringBuilder("1"));
                } else {
                    //assuming its always exit and entry
                    map.put(record[0], new StringBuilder("0"));
                }
            }
        }

        Set<String> noEntryResult = new HashSet<>();
        Set<String> noExitResult = new HashSet<>();

        for (Map.Entry<String, StringBuilder> entry : map.entrySet()) {
            String name = entry.getKey();
            String actions = entry.getValue().toString();
            System.out.println(name + "->" + actions);

            for (int i = 0; i < actions.length() - 1; i++) {
                if (i == 0 && actions.charAt(i) == '1') {
                    noEntryResult.add(name);
                } else {
                    if (actions.charAt(i) == actions.charAt(i + 1)) {
                        if (actions.charAt(i) == '0') {
                            noExitResult.add(name);
                        } else {
                            noEntryResult.add(name);
                        }
                    }
                }
            }
            if (actions.charAt(actions.length() - 1) == '0') {
                noExitResult.add(name);
            }
        }

        System.out.println("no entry: " + noEntryResult);
        System.out.println("no exit: " + noExitResult);
        System.out.println("-------------------------");
    }


    @Test
    public void testSecurityFinal() {
        String[][] input = {{"Martha", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Paul", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Paul", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Martha", "exit"},
        };
        securityFinal(input);

        String[][] input1 = {{"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"}
        };
        securityFinal(input1);

        String[][] input2 = {{"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        securityFinal(input2);

        String[][] input3 = {{"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"}
        };
        securityFinal(input3);

        String[][] input4 = {{"Paul", "enter"},
                {"Paul", "exit"}
        };
        securityFinal(input4);

        String[][] input5 = {{"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        securityFinal(input5);
    }

    @Test
    public void testSecurityFinal2() {
        String[][] input5 = {{"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        securityFinal(input5);
    }

    @Test
    public void testSecurity3Harsh() {
        String[][] input = {{"Martha", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Paul", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Paul", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Martha", "exit"},
        };
        security3(input);

        String[][] input1 = {{"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"}
        };
        security3(input1);

        String[][] input2 = {{"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        security3(input2);

        String[][] input3 = {{"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"}
        };
        security3(input3);

        String[][] input4 = {{"Paul", "enter"},
                {"Paul", "exit"}
        };
        security3(input4);

        String[][] input5 = {{"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        security3(input5);
    }

    @Test
    public void testSecurity () {

        String [][] input = {
                {"Martha",   "exit"},
                {"Paul",     "enter"},
                {"Martha",   "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "enter"},
                {"Paul",     "enter"},
                {"Curtis",   "exit"},
                {"Curtis",   "enter"},
                {"Paul",     "exit"},
                {"Martha",   "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "exit"},
                {"Paul",     "enter"},
                {"Paul",     "enter"},
                {"Martha",   "exit"}
        };

        List<List<String> > result = security( input );
        assert result.size() == 2;
        assert result.get(0).size() == 2;
        assert result.get(1).size() == 2;
    }


    @Test
    public void testSecurity2 () {

        String [][] input = {
                {"Paul",   "enter"},
                {"Paul",     "exit"},
                {"Paul",   "exit"},
                {"Paul",   "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
        };

        List<List<String> > result = security( input );
        assert result.size() == 2;
        assert result.get(0).size() == 1;
        assert result.get(1).size() == 1;
    }

    @Test
    public void testSecurity3 () {

        String [][] input = {
                {"Paul",   "enter"},
                {"Paul",     "enter"},
                {"Paul",   "exit"},
                {"Paul",   "exit"},
        };

        List<List<String> > result = security( input );
        assert result.size() == 2;
        assert result.get(0).size() == 1;
        assert result.get(1).size() == 1;
    }


}
