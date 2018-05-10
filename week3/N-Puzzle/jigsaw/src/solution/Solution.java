package solution;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        final int DIRS = 4;
        Set<JigsawNode> myVisitedList = new HashSet<>(1000);    // 用以保存已发现的节点
        Queue<JigsawNode> myExploreList = new LinkedList<JigsawNode>() {
			private static final long serialVersionUID = 1L;
		};  // 用以保存已发现但未访问的节点
        
        myExploreList.add(bNode);
        
        while (!myExploreList.isEmpty()) {
        	// 取出exploreList的第一个节点N，置为当前节点currentJNode
            // 若currentJNode为目标节点，则搜索成功，计算解路径，退出
            this.currentJNode = myExploreList.poll();
            if (this.currentJNode.equals(eNode)) {
                this.getPath();
                break;
            }

            JigsawNode[] nextNodes = new JigsawNode[]{
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };

            //	寻找所有与currentJNode邻接且未曾被发现的节点，将它们按代价估值从小到大排序插入exploreList中
            //	并加入visitedList中，表示已发现
            for (int i = 0; i < DIRS; i++) {
                if (nextNodes[i].move(i) && !myVisitedList.contains(nextNodes[i])) {
                    myVisitedList.add(nextNodes[i]);
                    myExploreList.add(nextNodes[i]);
                }
            }
        }  
        //System.out.println("Jigsaw BFS Search Result:");
        //System.out.println("Solution Path: ");
        //System.out.println(this.getSolutionPath());
        //System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return true;
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int error = 0; //不正确的个数
        int manHatten = 0;	//曼哈顿距离
        int geometric = 0;	//欧几里得距离
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            int x1 = (index - 1)/dimension + 1;
            int y1 = index % dimension == 0 ? dimension : index % dimension;
            int x2 = (jNode.getNodesState()[index] -1) /dimension + 1;
            int y2 = jNode.getNodesState()[index] % dimension == 0 ? dimension : jNode.getNodesState()[index] % dimension;
            int dx = Math.abs(x1-x2);
            int dy = Math.abs(y1-y2);
            manHatten += dx + dy;
            geometric += Math.sqrt(dx*dx+dy*dy);
        	if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        	if(jNode.getNodesState()[index] != index) {
        		error++;
        	}
        }
        s = (int)(1 * s + 1 * manHatten + 1 * geometric);
        jNode.setEstimatedValue(s);
    }
}
