package app.demo;

public class BasicService implements BasicAPI {
    
    private String _greeting = "Hello, world";

    @Override
    public void setGreeting(String greeting) {
        _greeting = greeting;
        System.out.println("set greeting success:" + _greeting);
    }

    @Override
    public String hello() {
        return _greeting;
    }

    @Override
    public User getUser() {
        return new User("prance", "meshow");
    }
}