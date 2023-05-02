package Client;

public class ThreadSharedData {
    private boolean isConnectionActive;
    public void setConnectionActive(boolean val){
        this.isConnectionActive = val;
    }
    public boolean getConnectionActive(){
        return this.isConnectionActive;
    }
}
