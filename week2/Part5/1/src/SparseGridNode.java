public class SparseGridNode
{
    private Object occupant;
    private int col;
    private SparseGridNode next;
    
    public SparseGridNode(Object o, int c, SparseGridNode nextNode) {
		occupant = o;
		col = c;
		next = nextNode;
	}
    
    public int getCol() {
    	return col;
    }
    
    public Object getOccupand() {
		return occupant;
	}
    
    public SparseGridNode getNext() {
    	return next;
    }
    
    public void setCol(int c) {
		col = c;
	}
    
    public void setOccupand(Object o) {
		occupant = o;
	}
    
    public void setNext(SparseGridNode nextNode) {
    	next = nextNode;
    }
}
