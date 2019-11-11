package org.cockloft.vertx.router.path;
import org.cockloft.vertx.router.Route;

import java.util.HashMap;
import java.util.Map;

public class RouterTree {
    private TreeNode rootTreeNode;
    public RouterTree(){
        super();
        this.rootTreeNode = new TreeNode();
        this.rootTreeNode.setNode(new Route());
        this.rootTreeNode.setNodePath("/");
    }

    static class TreeNode{
        private String nodePath;
        private Route node;
        private Map<String,TreeNode> next=new HashMap<>();
        public String getNodePath() {
            return nodePath;
        }
        public void setNodePath(String nodePath) {
            this.nodePath = nodePath;
        }
        public Route getNode() {
            return node;
        }
        public void setNode(Route node) {
            this.node = node;
        }
        public Map<String, TreeNode> getNextMap() {
            return next;
        }
        public TreeNode getNextNode(String path){
            return this.next.get(path);
        }
    }
    public void addPath(String path,Route route){

    }
    public Route getRouter(String path){
        return null;
    }
}
