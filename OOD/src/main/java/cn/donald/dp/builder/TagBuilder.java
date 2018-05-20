package cn.donald.dp.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DonaldY on 2017/7/18.
 */
public class TagBuilder {

    private TagNode rootNode;
    private TagNode currNode;
    private TagNode pareNode;

    public TagBuilder(String rootTagName){
        TagNode node = new TagNode(rootTagName);
        this.rootNode = node;
        this.currNode = node;
        this.pareNode = node;
    }

    public TagBuilder addChild(String childTagName){
        TagNode newNode = new TagNode(childTagName);
        this.currNode.add(newNode);
        this.pareNode = this.currNode;
        this.currNode = newNode;
        return this;
    }

    public TagBuilder addSibling(String siblingTagName){
        TagNode newNode = new TagNode(siblingTagName);
        this.pareNode.add(newNode);
        this.currNode = newNode;
        return this;
    }

    public TagBuilder setAttribute(String name, String value){
        this.currNode.setAttribute(name, value);
        return this;
    }

    public TagBuilder setText(String value){
        this.rootNode.setValue(value);
        return this;
    }

    public String toXML(){
        return this.rootNode.toXML();
    }
}
