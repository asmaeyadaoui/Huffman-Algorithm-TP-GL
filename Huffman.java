import java.util.*;
 
class HuffmanNode {
    int frequency;
    char character;
    HuffmanNode right;
    HuffmanNode left;
}
class QueueComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode x, HuffmanNode y)
	{
		return x.frequency - y.frequency;
	}
}
 
public class Huffman {

    public static HashMap<Character,String> compressedCode =  new HashMap<Character,String>();
    
    public static HashMap<Character,Integer> getFrequencies(String message){
        HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            Integer value = freq.get(c);
            if(value == null){
                freq.put(c, 1);
            }else{
                freq.put(c, value + 1);
            }
        }
        return freq;
    }

    public static void CompressCode(HuffmanNode root, String s)
    {
        if (root.left == null && root.right == null && Character.isLetter(root.character)) {
            System.out.println(root.character + ":" + s);
            compressedCode.put(root.character, s);
            return;
        }
        CompressCode(root.left, s + "0");
        CompressCode(root.right, s + "1");
    }
     public static void main(String[] args)
    {
        // the message to compress
        String message = "BCCABBDDAECCBBAEDDCC";

        // map of each character and its frequency
        HashMap<Character,Integer> charfreq = getFrequencies(message);

        // size of the map
        int size = charfreq.size();
        
        // queue to store nodes in increasing order of frequencies
        PriorityQueue<HuffmanNode> pqueue = new PriorityQueue<HuffmanNode>(size, new QueueComparator());
        
        // constructing all huffman nodes 
        for (HashMap.Entry<Character, Integer> e : charfreq.entrySet()){

            char c = e.getKey();
            int freq = e.getValue();
            
            HuffmanNode node = new HuffmanNode();

            node.character = c;
            node.frequency = freq;
            
            node.left = null;
            node.right = null;
            
            pqueue.add(node);
        }
 
        // assume the root is empty
        HuffmanNode root = null;
 
        // for each two minimal nodes in the queue , constuct a new node with the sum of their frequency
        while (pqueue.size() > 1) {

            HuffmanNode first = pqueue.peek();
            pqueue.poll();
 
            HuffmanNode second = pqueue.peek();
            pqueue.poll();
 
            HuffmanNode parent = new HuffmanNode();
           
            parent.frequency = first.frequency + second.frequency;
            parent.character = '#';
 
            parent.left = first;
            parent.right = second;
 
            root = parent;
 
            pqueue.add(parent);
        }

        System.out.println("Message : " + message);
 
        System.out.println("Original message size (each ASCII letter is coded in 8 bits) is " + 8 * message.length() + " bits");
        
        CompressCode(root, "");

        
        int totalCompressedBits = 0;
        for (HashMap.Entry<Character,String> e : compressedCode.entrySet()) {
            char character = e.getKey();
            // total compressed bits is the sum of each (character code length * its frenquency)
            totalCompressedBits+= e.getValue().length()*charfreq.get(character); 
        }

        System.out.println("Huffan compressed message size (using the table above) is " + totalCompressedBits + " bits");
    }
}


