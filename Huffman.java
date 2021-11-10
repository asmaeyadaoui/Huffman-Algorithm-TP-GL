import java.util.*;
 
class HuffmanNode {
 
    int data;
    char c;
    HuffmanNode right;
    HuffmanNode left;
    
}
class MyComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode x, HuffmanNode y)
	{

		return x.data - y.data;
	}
}
 
