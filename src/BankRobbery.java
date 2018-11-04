


import java.util.*;


class Node {

    private String name;

    private List<Node> shortestPath = new LinkedList<>();

    private List<Integer> arrayList = new ArrayList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public List<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }
}


public class BankRobbery {

    public static int calculateShortestPathFromSource(int srcDist, Node source, Node dest) {

        source.setDistance(srcDist);

        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {

            Node currentNode = getLowestDistanceNode(unsettledNodes);

            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {

                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode, dest);
                unsettledNodes.add(adjacentNode);

            }
            //System.out.println(currentNode.getName());

        }
        Collections.sort(dest.getArrayList());
        return dest.getArrayList().get(0);
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void CalculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode, Node dest) {

        evaluationNode.setDistance(edgeWeigh);
        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
        shortestPath.add(sourceNode);
        //shortestPath.add(evaluationNode);
        evaluationNode.setShortestPath(shortestPath);

        if (evaluationNode.getName() == dest.getName()) {
            List<Node> list = evaluationNode.getShortestPath();
            Set<Integer> uniqueSet = new HashSet<>();

            for (Node node : list) {
                if (!uniqueSet.contains(node.getDistance())) {
                    uniqueSet.add(node.getDistance());
                }

            }
            uniqueSet.add(evaluationNode.getDistance());
            evaluationNode.getArrayList().add(uniqueSet.size());
        }

    }

    public static void main(String[] args) {


        int t,n,m;


        Scanner sc = new Scanner(System.in);


        System.out.print("Enter number of Test Cases: ");

        t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            System.out.println("For test case: " + i + 1);

            System.out.print("Enter number of rooms: ");
            n = sc.nextInt();

            System.out.print("Enter total number of corridors: ");
            m = sc.nextInt();


            Node node[] = new Node[n];

            for (int j = 0; j < n; j++) {
                node[j] = new Node("r");
            }

            System.out.println("Add the corridoors to the rooms in 'SEQUENCE' and select keys between [0-9]: ");


            int room1 = 0;
            int start = 0;
            for (int j = 0; j < m; j++) {

                if (j == 0) {
                    System.out.print("Enter source room num from 1: ");

                    start = room1 = sc.nextInt();
                }

                else {
                    System.out.print("Enter first room num from 1: ");
                    room1 = sc.nextInt();
                }

                System.out.print("Enter second room num from 1: ");
                int room2 = sc.nextInt();

                System.out.print("Select keys to unlock 2nd room between [0-9]: ");

                int key = sc.nextInt();

                node[room1 - 1].addDestination(node[room2 - 1], key);


            }




            System.out.print("Enter the destination room number: ");

            int destination = sc.nextInt();

            System.out.print("Enter source room keys[0-9]: ");

            int srcKeys = sc.nextInt();

            int minKeys;

            minKeys = BankRobbery.calculateShortestPathFromSource(srcKeys, node[start - 1], node[destination - 1]);

            System.out.println("Distinct keys needed to unlock vault: " + minKeys);

        }


        /*Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        *//*Node nodeE = new Node("E");
        Node nodeF = new Node("F");*//*
        int srcDist = 4;

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 4);

        nodeB.addDestination(nodeD, 3);

        nodeC.addDestination(nodeD, 4);
        nodeD.addDestination(nodeE, 3);
        *//*nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);*//*


        int minKeys;

        minKeys = DijakstraAlgo.calculateShortestPathFromSource(srcDist, nodeA, nodeE);*/








        /*Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        int srcDist = 4;

        nodeA.addDestination(nodeB, 1);
        nodeA.addDestination(nodeC, 5);

        nodeB.addDestination(nodeD, 2);

        nodeC.addDestination(nodeD, 2);
        nodeD.addDestination(nodeE, 2);

        nodeC.addDestination(nodeE, 6);

        //nodeD.addDestination(nodeE, 2);
        //nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 4);


        int minKeys;

        minKeys = DijakstraAlgo.calculateShortestPathFromSource(srcDist, nodeA, nodeE);

        System.out.println(minKeys);*/
    }

}
