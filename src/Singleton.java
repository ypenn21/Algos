public class Singleton {

    private String something;

    private static Singleton single;

    private Singleton() {
        Singleton single = new Singleton();
        something = "fuck you servicenow";
    }


    public void setSomething(String something){
        this.something = something;
    }

    public String getSomething(){
        return this.something;
    }

    public static Singleton getSingleton() {
        return single;
    }

    public static void main(String args[]) {
        Singleton singleton = Singleton.getSingleton();
        singleton.setSomething("something");
        String something = singleton.getSomething();
        System.out.println(something);
    }



}

