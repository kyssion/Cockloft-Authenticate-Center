package com.cockloft.core.bean;

import com.cockloft.core.server.Server;

import java.util.HashMap;

public class PathTree {
    private String path;
    private HashMap<String, PathTree> nextPathTree;
    private Server server;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, PathTree> getNextPathTree() {
        return nextPathTree;
    }

    public void setNextPathTree(HashMap<String, PathTree> nextPathTree) {
        this.nextPathTree = nextPathTree;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
