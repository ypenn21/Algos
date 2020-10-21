package yanni.com.unit.warmups;

import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.stream.Collectors;

public class AbsolutePaths {

    public String findThePath(String folderPathWithDots) {
        String [] folders = folderPathWithDots.split("/");
        Integer index = 0;

        Stack<String> myFolders = new Stack<>();

        for(String folder : folders) {
            if (folder.equals(".")) {
                continue;
            } else if (folder.equals("..")) {
                myFolders.pop();
            } else {
                myFolders.add(folder);
            }
        }

        System.out.println(myFolders.toString());
        StringBuilder b = new StringBuilder();
        myFolders.stream().forEach(str-> {
            b.append(str+"/");
        });

        System.out.println(b.toString().substring(0, b.toString().length()-1));

        return myFolders.stream().map(Object::toString).collect(Collectors.joining("/"));
    }

    @Test
    public void testFindThePath() {

        String myPathWithDots = "/users/tech/docs/.././desk/../";

        System.out.println(findThePath(myPathWithDots));
    }




}
