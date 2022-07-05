package com.moodleeducation.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@Data
public class TreeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String text;
    private List<TreeNode> nodes;
    private Map<String,Boolean> state;

    public TreeNode() {
    }

    public TreeNode(int id, String text, List<TreeNode> nodes) {
        this.id = id;
        this.text = text;
        this.nodes = nodes;
    }
}
