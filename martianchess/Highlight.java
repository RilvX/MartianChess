package martianchess;

public class Highlight {
    public static enum Type{Place, Take, Promotion}
    private int xpos;
    private int ypos;
    private Type type;
    
    Highlight(int x, int y, Type _type){
        xpos = x;
        ypos = y;
        type = _type;
    }
    public int getXpos(){
        return(xpos);
    }
    public int getYpos(){
        return(ypos);
    }
    public Type getType(){
        return(type);
    }
}
