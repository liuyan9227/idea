package com.myserver.dataStructure.linked;

/**
 * 实现链表
 *
 * 1.调用LinkList的add方法时,调用依次就会重新开辟一个内存空间用作保存值
 * 2.第一次add,first(null,null)->(1,null)的value值为1,next值为null
 * 3.第二次add,first(1,null)->(1,(2,null))会和临时变量temp会同时指向第一个内存空间,并将第二个值保存在next值中
 * 4.第三次add,first(1,(2,null))->(2,(3,null))会和另一个临时变量temp同时指向一个空间,并将next值改为value值,并保存值到next节点
 * [1,[2,[3,[4,null]]]]
 *
 * toString方法跟add类似使用的是append方法来拼装字符串的
 * [ + value值 + 判断是否有next来确定加不加 , + 当next元素为null时 + ]
 */
public class LinkList {

    private Node first;
    // 链表大小,默认值为0
    private int size;

    public Node getFrist() {
        return first;
    }

    public void setFrist(Node frist) {
        this.first = frist;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    /**
     * 添加节点的方法
     *
     * @param obj 传入的数据
     */
    public void add(Object obj){
        // 1.把用户传入的数据打包, node.getValue() == obj | node.getNext() == null
        Node node = new Node(obj);

        // 2.把打好包的数据挂在first上面
        if(first == null){
            // 如果是第一次添加,将数据直接放在first上面
            first = node;
        } else {
            // 如果first上有值,先将值赋值在临时变量上
            Node temp = first;
            System.out.println("temp和first是否指向的是同一块内存空间 : " + (temp == first));
            // 不是第一次添加,寻找最后一个节点,并存放
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(node);
        }
        System.out.println("每次得到的first的value值为 : " + first.getValue());
        System.out.println("每次得到的first的next值为 : " + first.getNext());
        size++;
    }


    /**
     * 重写toString方法
     */
    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        // StringBuffer是线程安全的
        stringBuffer.append("[");
        // 使用一个临时变量储存
        Node temp = first;
//        System.out.println(first.getNext()); // 指向的是内存空间,是LinkList的下一个元素,有种嵌套的感觉
        // 当变量有值时
        if(temp != null){
            // 若没有下一个节点的时候,说明只有一个元素
            if(temp.getNext() == null){
                // 当为第一个元素的时候
                stringBuffer.append(temp.getValue());
            } else {
                // 若有下一个元素,就在当前的元素后面跟逗号
                stringBuffer.append(temp.getValue()).append(",");
            }
        }

        // 若存在下一个值的时候
        while(temp.getNext() != null){
            // 指针向下移动一个位置
            temp = temp.getNext();
            // 当没有下一个节点的时候,现在为最后的一个节点
            if(temp.getNext() == null){
                // 最后一个节点
                stringBuffer.append(temp.getValue());
            } else {
                stringBuffer.append(temp.getValue()).append(",");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
