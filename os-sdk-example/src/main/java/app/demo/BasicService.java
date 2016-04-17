package app.demo;

public class BasicService implements BasicAPI {
    
    private String _greeting = "Hello, world";

    public void setGreeting(String greeting) {
        _greeting = greeting;
        System.out.println("set greeting success:" + _greeting);
    }

    public String hello() {
        return _greeting;
    }

    public User getUser() {
        return new User("prance", "meshow");
    }
}