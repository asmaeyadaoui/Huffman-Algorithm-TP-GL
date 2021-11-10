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
 
public class Huffman {
 
    public static void printCode(HuffmanNode root, String s)
    {
 
        
        if (root.left
                == null
            && root.right
                   == null
            && Character.isLetter(root.c)) {
 
            System.out.println(root.c + ":" + s);
 
            return;
        }
 
        
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
     public static void main(String[] args)
    {
    }
}

