package com.donald.filesystem;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author donald
 * @date 2021/07/13
 */
public class FSDirectory {

    /**
     * 内存中的文件树
     */
    private INodeDirectory dirTree;

    public FSDirectory() {
        this.dirTree = new INodeDirectory("/");
    }

    /**
     * 创建目录
     *
     * 1. 对目录分片
     * 2. 遍历查找是否存在对应目录
     * @param path 目录路径
     */
    public void mkdir(String path) {

        synchronized (dirTree) {

            String [] pathes = path.split("/");

            INodeDirectory parent = null;

            for (String splitedPath : pathes) {
                if ("".equals(splitedPath.trim())) {
                    continue;
                }

                // 从哪开始找
                INodeDirectory tmp = null == parent ? dirTree : parent;

                INodeDirectory dir = findDirectory(tmp, splitedPath);
                if (null != dir) {
                    parent = dir;
                    continue;
                }
                INodeDirectory child = new INodeDirectory(splitedPath);
                parent.addChild(child);
            }
        }
    }

    /**
     * 对文件目录树递归查找目录
     * @param dir 目录
     * @param path 路径
     * @return 目录
     */
    private INodeDirectory findDirectory(INodeDirectory dir, String path)  {
        if (dir.getChildren().size() == 0) {
            return null;
        }

        INodeDirectory resultDir = null;

        for (INode child : dir.getChildren()) {
            if (child instanceof INodeDirectory) {
                INodeDirectory childDir = (INodeDirectory) child;

                if (childDir.getPath().equals(path)) {
                    return childDir;
                }
                resultDir = findDirectory(childDir, path);

                if (resultDir != null) {
                    return resultDir;
                }
            }
        }

        return null;
    }

    /**
     * 文件目录树中的一个节点
     */
    public interface INode {

    }

    /**
     * 文件目录树中的一个目录
     */
    public static class INodeDirectory implements INode {
        private String path;
        private List<INode> children;

        public INodeDirectory(String path) {
            this.path = path;
            this.children = new LinkedList<>();
        }

        public void addChild(INode node) {
            this.children.add(node);
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<INode> getChildren() {
            return children;
        }

        public void setChildren(List<INode> children) {
            this.children = children;
        }
    }

    /**
     * 文件目录树中一个文件
     */
    public static class INodeFile implements INode {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
