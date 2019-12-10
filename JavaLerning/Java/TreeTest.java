import java.text.DecimalFormat;
import java.util.*;

//���ڹ������������������ַ���ѹ����ƽ������, ƽ����Ϣ��, ����

//���Գ���
public class TreeTest {

    public static void main(String[] args) {

        try { //������ܳ��ֵ��쳣, ���ָ���쳣��

            Tree tree = new Tree();

            Scanner s = new Scanner(System.in);

            String input = s.nextLine();


            List<Node> nodes = tree.getNodes(input);

            //System.out.println(nodes); //���Դ���
            //System.out.println(nodes.size()); //���Դ���
            


            Node root = tree.createTree(nodes);

            tree.setEncodeLength(root);

            //System.out.println(root.leftNode); //���Դ���
            //System.out.println(root.rightNode.hasLeftNode()); //���Դ���

            List<Node> encodeNodes = new ArrayList<Node>();


            tree.preOrder(root, encodeNodes);

            System.out.println(encodeNodes);

            DecimalFormat df = new DecimalFormat("0.00"); //������λС��

            double middleLength = tree.middleLength(encodeNodes);
            double entropy = tree.entropy(encodeNodes);
            double redundance = tree.redundance(encodeNodes);

            System.out.println("���泤��: " + df.format(middleLength) + " Bit");
            System.out.println("ƽ����Ϣ��: " + df.format(entropy));
            System.out.println("����: " + df.format(redundance));

            
            s.close();

        } catch (Exception e) {

            e.printStackTrace();
        
        }

    }

}


//������������, �����нڵ�洢������
class Tree {
  
    Node root;
    
    
    //��������ַ���ת��Ϊ�洢�ڵ���б�
    public List<Node> getNodes(String input) {
    
        Map<Character, Integer> map = statistics(input);
        
        List<Node> nodes = new ArrayList<Node>();
        
        double subWeight = 0.0;
        
        Character[] keys = map.keySet().toArray(new Character[0]);
        
        for(Character c : keys) {
        
            subWeight += map.get(c); //����ַ�������
        
        }
        
        for(Character c : keys) {
        
            Node node = new Node(c.toString(), map.get(c) / subWeight); //���ýڵ�, ���ַ���Ϣ������Ƶ�ʸ�ֵ
        
            nodes.add(node);
        
        }
        
        return nodes;
    
    }
    
    //��������ַ��������ַ������ִ���������Map��
    public Map<Character, Integer> statistics(String input) {
        
        if(input == null || "".equals(input)) return null;
    
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        
        for(int i = 0; i < input.length(); i++) {
        
            char c = input.charAt(i);
        
            Integer val = map.get(c);
        
            if(val != null) map.put(c, new Integer(val + 1));
        
            else map.put(c,1);
        
        }
        
        return map;
    
    }
    
    
    //������������
    //���ķ���, ���Ƚ��洢�ڵ��List����Ȩ�ش�С��������
    //��Ȩ����С�������ڵ�ֱ�����Ϊ���ҽڵ�, �������½ڵ�
    //�ظ���������, ֱ���б��н�ʣ���ڵ�
    //���˹��������������
    public Node createTree(List<Node> nodes) {
    
        while(nodes.size() > 1) {
        
            quicksort(nodes);
        
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
        
        
            Node parent = new Node(left.chars + right.chars, left.weight + right.weight);
        
            parent.leftNode = left;
            parent.leftNode.parent = parent;

            parent.rightNode = right;
            parent.rightNode.parent = parent;
        
            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
        
            nodes.add(parent);
        
        }
        
        return nodes.get(0);
    
    }
    
    
    
    
    //���������㷨
    public void quicksort(List<Node> nodes) {
    
        subsort(nodes, 0, nodes.size() - 1);
    
    }
    
    public void subsort(List<Node> nodes, int start, int end) {
    
        if(start >= end || nodes == null || nodes.size() <= 1) return;
        
        int i = start;
        int j = end;
        Node base = nodes.get(start);
        
        while(i < j) {
        
            while(i < j && nodes.get(j).weight <= base.weight) j--;
            
            if(i < j) {
                
                nodes.set(i, nodes.get(j));
                i++;

            }
            
            while(i < j && nodes.get(i).weight >= base.weight) i++;
            
            if(i < j) {
            
                nodes.set(j, nodes.get(i));
                j--;
            
            }
        
        }
        
        nodes.set(i, base);
        
        subsort(nodes, start, i - 1);
        subsort(nodes, i + 1, end);
    
    }


    //��������������ɺ�, �Ը����ڵ������ľ�����м��㲢��ֵ
    public void setEncodeLength(Node root) {

        Node currentNode = root;

        if(currentNode.hasLeftNode()) {

            currentNode.leftNode.encodeLength = currentNode.encodeLength + 1;
            setEncodeLength(currentNode.leftNode);

        }

        if(currentNode.hasRightNode()) {

            currentNode.rightNode.encodeLength = currentNode.encodeLength + 1;
            setEncodeLength(currentNode.rightNode);

        }

    }


    //ʹ���������ǰ�����, ��ȡҶ�ӽڵ�, ���䴢�浽List��
    public void preOrder(Node root, List<Node> encodeNodes) {

        if(root == null) return;

        Stack<Node> stack = new Stack<Node>();

        stack.push(root);

        while(!(stack.isEmpty())) {

            Node tempNode = stack.pop();

            if(tempNode.hasRightNode()) stack.push(tempNode.rightNode);
            if(tempNode.hasLeftNode()) stack.push(tempNode.leftNode);

            if(!(tempNode.hasLeftNode()) && !(tempNode.hasRightNode())) encodeNodes.add(tempNode);

        }

    }
    
    //����ƽ���洢����
    public double middleLength(List<Node> encodeNodes) {

        double length = 0.0;

        for(Node node : encodeNodes) {

            length += node.weight * node.encodeLength;

        }

        return length;

    }

    //������(ƽ����Ϣ��)
    public double entropy(List<Node> encodeNodes) {

        double e = 0.0;

        for(Node node : encodeNodes) {

            e += (-node.weight * (Math.log(node.weight) / Math.log(2)));

        }

        return e;

    }

    //��������
    public double redundance(List<Node> encodeNodes) {

        double r = this.middleLength(encodeNodes) - this.entropy(encodeNodes);

        return r;

    }

}


//����ڵ�
class Node {
        
    //Fields
    
    String chars; //�ڵ�������ַ���
    double weight; //�ڵ��Ȩ��
    
    //�������ڵ㼰�����ӽڵ�
    Node parent;
    Node leftNode;
    Node rightNode;

    //���������ڵ�·������
    int encodeLength = 0;
    
    //Constructor
    
    public Node(String chars, double weight) {
    
        super();
        this.chars = chars;
        this.weight = weight;
    
    }

    //Method
    
    public boolean isRoot() {
    
        return parent == null;
    
    }
    
    public boolean isLeftNode() {
    
        return parent != null && this == parent.leftNode;
    
    }

    public boolean hasLeftNode() {

        return this.leftNode != null;

    }

    public boolean hasRightNode() {

        return this.rightNode != null;

    }

    public boolean isRightNode() {

        return parent != null && this == parent.rightNode;

    }
    
    public String toString() {
    
        return "Node["+ chars + ", " + parent + ", " + weight +"]";
    
    }

}