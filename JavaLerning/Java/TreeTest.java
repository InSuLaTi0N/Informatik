import java.text.DecimalFormat;
import java.util.*;

//关于构建哈夫曼树及计算字符串压缩后平均长度, 平均信息量, 冗余

//测试程序
public class TreeTest {

    public static void main(String[] args) {

        try { //捕获可能出现的异常, 如空指针异常等

            Tree tree = new Tree();

            Scanner s = new Scanner(System.in);

            String input = s.nextLine();


            List<Node> nodes = tree.getNodes(input);

            //System.out.println(nodes); //测试代码
            //System.out.println(nodes.size()); //测试代码
            


            Node root = tree.createTree(nodes);

            tree.setEncodeLength(root);

            //System.out.println(root.leftNode); //测试代码
            //System.out.println(root.rightNode.hasLeftNode()); //测试代码

            List<Node> encodeNodes = new ArrayList<Node>();


            tree.preOrder(root, encodeNodes);

            System.out.println(encodeNodes);

            DecimalFormat df = new DecimalFormat("0.00"); //保留两位小数

            double middleLength = tree.middleLength(encodeNodes);
            double entropy = tree.entropy(encodeNodes);
            double redundance = tree.redundance(encodeNodes);

            System.out.println("储存长度: " + df.format(middleLength) + " Bit");
            System.out.println("平均信息量: " + df.format(entropy));
            System.out.println("冗余: " + df.format(redundance));

            
            s.close();

        } catch (Exception e) {

            e.printStackTrace();
        
        }

    }

}


//构建哈夫曼树, 将所有节点存储至其中
class Tree {
  
    Node root;
    
    
    //将输入的字符串转换为存储节点的列表
    public List<Node> getNodes(String input) {
    
        Map<Character, Integer> map = statistics(input);
        
        List<Node> nodes = new ArrayList<Node>();
        
        double subWeight = 0.0;
        
        Character[] keys = map.keySet().toArray(new Character[0]);
        
        for(Character c : keys) {
        
            subWeight += map.get(c); //获得字符串长度
        
        }
        
        for(Character c : keys) {
        
            Node node = new Node(c.toString(), map.get(c) / subWeight); //设置节点, 将字符信息及出现频率赋值
        
            nodes.add(node);
        
        }
        
        return nodes;
    
    }
    
    //将输入的字符串按照字符及出现次数储存在Map中
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
    
    
    //构建哈夫曼树
    //核心方法, 首先将存储节点的List按照权重大小进行排序
    //将权重最小的两个节点分别设置为左右节点, 并构建新节点
    //重复上述内容, 直到列表中仅剩根节点
    //至此哈夫曼树构建完成
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
    
    
    
    
    //快速排序算法
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


    //哈夫曼树建立完成后, 对各个节点至根的距离进行计算并赋值
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


    //使用深度优先前序遍历, 获取叶子节点, 将其储存到List中
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
    
    //计算平均存储长度
    public double middleLength(List<Node> encodeNodes) {

        double length = 0.0;

        for(Node node : encodeNodes) {

            length += node.weight * node.encodeLength;

        }

        return length;

    }

    //计算熵(平均信息量)
    public double entropy(List<Node> encodeNodes) {

        double e = 0.0;

        for(Node node : encodeNodes) {

            e += (-node.weight * (Math.log(node.weight) / Math.log(2)));

        }

        return e;

    }

    //计算冗余
    public double redundance(List<Node> encodeNodes) {

        double r = this.middleLength(encodeNodes) - this.entropy(encodeNodes);

        return r;

    }

}


//构造节点
class Node {
        
    //Fields
    
    String chars; //节点包含的字符串
    double weight; //节点的权重
    
    //构建父节点及左右子节点
    Node parent;
    Node leftNode;
    Node rightNode;

    //构建根到节点路径长度
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