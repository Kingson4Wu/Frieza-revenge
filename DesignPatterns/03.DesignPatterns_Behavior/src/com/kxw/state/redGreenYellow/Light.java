package com.kxw.state.redGreenYellow;

public class Light {  
	  
    private State state;  
      
    private void change(){  
        state.change(this);  
    }  
  
    public void work(){  
        while(true){  
            change();  
        }  
    }  
    public Light(State state) {  
        super();  
        this.state = state;  
    }  
  
    public State getState() {  
        return state;  
    }  
  
    public void setState(State state) {  
        this.state = state;  
    }  
      
}  