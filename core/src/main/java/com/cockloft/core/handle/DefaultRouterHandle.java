package com.cockloft.core.handle;

import com.cockloft.core.bean.PathTree;
import com.cockloft.core.server.ErrorServer;
import com.cockloft.core.server.RootServer;
import com.cockloft.core.server.Server;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class DefaultRouterHandle extends RouterHandle {
    @Override
    public void handle(HttpServerRequest event) {

    }

    Logger logger = LoggerFactory.getLogger(DefaultRouterHandle.class);

    private PathTree rootPathtree;

    private String rootPath;

    public DefaultRouterHandle() {
        this("");
    }

    public DefaultRouterHandle(String rootPath) {
        super();
        this.rootPath = rootPath;
        rootPathInit();
    }

    /**
     * 初始化根root
     *
     * @throws Exception
     */
    private void rootPathInit() {
        PathTree pathTree = new PathTree();
        pathTree.setNextPathTree(new HashMap<>());
        try {
            pathTree.setServer(new RootServer("", HttpMethod.GET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pathTree.setNextPathTree(new HashMap<>());
        pathTree.setPath("");
        this.rootPathtree = pathTree;
    }

    /**
     * 添加相关的信息到 httphandle中
     *
     * @param server
     * @return
     */
    public boolean addServer(Server server) {
        try {
            String[] pathNode = server.getPath().split("/");
            PathTree itemPath = this.rootPathtree;
            for (String aPathNode : pathNode) {
                if (!itemPath.getNextPathTree().containsKey(aPathNode)) {
                    PathTree pathTree = new PathTree();
                    pathTree.setPath(aPathNode);
                    pathTree.setNextPathTree(new HashMap<>());
                    itemPath.getNextPathTree().put(aPathNode, pathTree);
                    itemPath = pathTree;
                } else {
                    itemPath = itemPath.getNextPathTree().get(aPathNode);
                }
            }
            itemPath.setServer(server);
        } catch (Exception e) {
            logger.error("添加处理节点失败:error:{}|path:{}|httpHandle:{}", e.getMessage(), server.getPath(), server.toString());
            return false;
        }
        return true;
    }

    /**
     * 遍历树查找指定的节点的处理器,使用最长匹配
     *
     * @param node
     * @return
     */
    public Server findhandle(String[] node, PathTree nowPath) {
        Server server = nowPath.getServer();
        for (String s : node) {
            if (!nowPath.getNextPathTree().containsKey(s)) {
                return ErrorServer.errorServer;
            }
            server = nowPath.getNextPathTree().get(s).getServer();
        }
        return server != null ? server : ErrorServer.errorServer;
    }
}
