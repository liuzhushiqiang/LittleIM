/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qq.client.view;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author
 */
class JTreeCellRenderer extends DefaultTreeCellRenderer {

    public JTreeCellRenderer() {
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree,Object value,
            boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (node.getUserObject() instanceof JTreeBean) {
        }
        else
        {
            System.out.println(node.getUserObject()+"wocao");
        }
        JTreeBean jtb = (JTreeBean) node.getUserObject();
        setIcon(jtb.getIcon());
        setText(jtb.getString());
        setTextNonSelectionColor(jtb.getColor());
        //    setForeground(Color.RED);
        // setTextSelectionColor(Color.BLUE);
        //  setBackgroundSelectionColor(Color.GREEN);
        //  setBackgroundNonSelectionColor(Color.BLUE);
        return this;
    }

    protected boolean isTutorialBook(Object value) {
        DefaultMutableTreeNode node =(DefaultMutableTreeNode) value;

        return false;
    }
}
