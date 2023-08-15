package cn.vincent.design.behaviorModel.iterator;

/**
 * 行为型 - 迭代器模式
 */
public class main {

    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();
        for (Iterator iter = myCollection.getIterator(); iter.hasNext(); )
            System.out.println((String) iter.next());
    }
}
